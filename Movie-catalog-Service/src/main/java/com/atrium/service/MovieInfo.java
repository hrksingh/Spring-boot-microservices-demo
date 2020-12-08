package com.atrium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atrium.models.CatalogItem;
import com.atrium.models.Movie;
import com.atrium.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	/*
	 * Bulk Head Pattern to set Thread size for Api or microservice
	 * 
	 * @HystrixCommand( fallbackMethod = "getFallbackCatalogItem", threadPoolKey = "movieInfoPool", threadPoolProperties = {
	 * 
	 *                   @HystrixProperty(name = "coreSize", value = "20"),
	 * 
	 *                   @HystrixProperty(name = "maxQueueSize", value = "10") })
	 */

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")})
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://Movie-Info-Service:8082/movies/" + rating.getMovieId(),
				Movie.class);
		return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
	}

	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("No Movie", "No Desc", 0f);
	}

}
