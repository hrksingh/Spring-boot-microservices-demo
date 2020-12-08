package com.atrium.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atrium.models.Rating;
import com.atrium.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackUserRatings", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")})
	public List<Rating> getUserRatings(String userId) {
		UserRating userRating = restTemplate.getForObject("http://Rating-Data-Service:8083/ratings/user/" + userId,UserRating.class);
		List<Rating> ratings = userRating.getRatings();
		return ratings;
	}

	public List<Rating> getFallbackUserRatings(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setRatings(Collections.singletonList(new Rating(userId, 0f)));
		return userRating.getRatings();
	}

}
