package com.cybrilla.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cybrilla.dao.UrlShortnerDao;
import com.cybrilla.model.UrlPOJO;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class UrlShortnerService {

	@Autowired
	private UrlShortnerDao dao;
	
	@PersistenceContext
	private EntityManager em;
	
	Long counter = 1L;
	String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String shortenUrl(UrlPOJO url) {
		String returnUrl="";
		if(validateUrl(url)) {
			List<UrlPOJO> urlList = dao.findByUrl(em,url.getUrl());
			if(urlList.size()==0) {
				String shortUrl = encode(url.getUrl());
				url.setShortURl(shortUrl);
				dao.save(url);
				returnUrl = url.getShortURl();
			}
			else{	
				  returnUrl = urlList.get(0).getShortURl();
			}
		}
		else {
			throw new ResponseStatusException(
					  HttpStatus.BAD_REQUEST, "Invalid URL"
					);
		}
		return returnUrl;
	}
	
	public String getOriginalUrl(UrlPOJO url) {
		String returnUrl="";
		if(validateUrl(url)) {
			List<UrlPOJO> shortUrlList = dao.findByShortUrl(em,url.getUrl());
			if(shortUrlList.size()!=0) {
			returnUrl = shortUrlList.get(0).getUrl();
			}
			else {
				throw new ResponseStatusException(
						  HttpStatus.NOT_FOUND, "URL not found"
						);
			}
		}
		else {
			throw new ResponseStatusException(
					  HttpStatus.BAD_REQUEST, "Invalid URL"
					);
		}
		return returnUrl;
	}
	
	public String encode(String longUrl) {
		String shortUrl = "http://tinyurl.com/"+base62Encode(counter);
		counter++;
	    return shortUrl;
	}
	
	private String base62Encode(long value) {
	    StringBuilder sb = new StringBuilder();
	    while (value != 0) {
	        sb.append(base62.charAt((int)(value % 62)));
	        value /= 62;
	    }
	    while (sb.length() < 6) {
	        sb.append(0);
	    }
	    return sb.reverse().toString();
	}
	
	public boolean validateUrl(UrlPOJO url) {
		String urlCheck = url.getUrl();
		String regex = "((http|https)://)(www.)?" + "[a-zA-Z0-9@:%._\\+~#?&//=]" + "{2,256}\\.[a-z]"
				+ "{2,6}\\b([-a-zA-Z0-9@:%" + "._\\+~#?&//=]*)";

		Pattern p = Pattern.compile(regex);

		if (urlCheck == null) {
			return false;
		}
		Matcher m = p.matcher(urlCheck);
		return m.matches();
	}

}
