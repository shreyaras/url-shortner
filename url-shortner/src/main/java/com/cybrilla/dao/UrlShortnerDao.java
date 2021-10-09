package com.cybrilla.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cybrilla.model.UrlPOJO;

@Repository
public interface UrlShortnerDao extends CrudRepository<UrlPOJO, Integer> {
 
	public default List<UrlPOJO> findByUrl(EntityManager em, String url) {
		Query query = em.createQuery("from UrlPOJO where url=:url");
		query.setParameter("url", url);
		List<UrlPOJO> urlList = query.getResultList();
		return urlList;
	}
	
	public default List<UrlPOJO> findByShortUrl(EntityManager em, String url) {
		Query query = em.createQuery("from UrlPOJO where shortURl=:shortURl");
		query.setParameter("shortURl", url);
		List<UrlPOJO> shortUrlList = query.getResultList();
		return shortUrlList;
	}
}
