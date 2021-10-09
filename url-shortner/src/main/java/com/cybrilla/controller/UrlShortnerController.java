package com.cybrilla.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cybrilla.model.UrlPOJO;
import com.cybrilla.service.UrlShortnerService;

@RestController
public class UrlShortnerController {
	
	@Autowired
	private UrlShortnerService service;
	
	@PostMapping("/shortenUrl")
	public String shortenUrl(@RequestBody UrlPOJO url) {
	 return service.shortenUrl(url);
	}
	
	@PostMapping("/getOriginalUrl")
	public String getOriginalUrl(@RequestBody UrlPOJO url) {
		return service.getOriginalUrl(url);
	}

}
