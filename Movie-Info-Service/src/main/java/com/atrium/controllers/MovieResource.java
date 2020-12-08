package com.atrium.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atrium.models.Movie;
import com.atrium.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	private static HashMap<String, Movie> movieInfoDB = new HashMap<>();
	
	@Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

	static {
		movieInfoDB.put("A1", new Movie("A1", "Iron Man", "Good Sci Fi Movie"));
		movieInfoDB.put("A2", new Movie("A2", "Transformer", "Robots and boring humans"));
		movieInfoDB.put("A3", new Movie("A3", "Lord of Rings", "Dwarfs, Ogres, Elves you name it we got it"));
		movieInfoDB.put("A4", new Movie("A4", "Dark Knight", "Its Batman do I need to Say More"));
	}
   
	/*
	 * //static
	 * 
	 * @RequestMapping("/{movieId}") public Movie
	 * getMovieInfo(@PathVariable("movieId") String movieId) { return
	 * movieInfoDB.get(movieId); }
	 * 
	 */
	
	//External Api Call
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}

}
