package com.atrium.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atrium.models.CatalogItem;
import com.atrium.models.Rating;
import com.atrium.service.MovieInfo;
import com.atrium.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogResourceController {
	
	@Autowired
	private  UserRatingInfo userRatingInfo;
	
	@Autowired
    private MovieInfo movieInfo;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		//before @LoadBalanced
		//UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratings/user/" + userId, UserRating.class);
		
		//After @LoadBalanced
		List<Rating> ratings = userRatingInfo.getUserRatings(userId);

        return ratings.stream()
                      .map(rating -> {return movieInfo.getCatalogItem(rating);})
                      .collect(Collectors.toList());
	}

}
