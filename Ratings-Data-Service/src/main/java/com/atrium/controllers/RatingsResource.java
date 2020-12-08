package com.atrium.controllers;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atrium.models.Rating;
import com.atrium.models.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

	private static HashMap<String, UserRating> UserRatingDB = new HashMap<>();

	static {
		UserRatingDB.put("U1",new UserRating("U1", Arrays.asList(new Rating("100", 4f), new Rating("500", 4f))));
		UserRatingDB.put("U2",new UserRating("U2", Arrays.asList(new Rating("200", 5f))));
		UserRatingDB.put("U3", new UserRating("U3", Arrays.asList(new Rating("300", 3.5f), new Rating("100", 4.5f))));
		UserRatingDB.put("U4",new UserRating("U4",Arrays.asList(new Rating("400", 2f), new Rating("200", 3f), new Rating("600", 1f))));
	}

	@RequestMapping("/user/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		return UserRatingDB.get(userId);
	}
}
