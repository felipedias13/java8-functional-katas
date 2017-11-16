package katas;

import model.Movie;
import util.DataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: use map() to project an array of videos into an array of {id, title}-pairs
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys")
*/
public class Kata1 {
    public static List<Map> execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
        		.map(movie -> {
	        		Map<String, String> currentMovie = new HashMap<String, String>();
	        		currentMovie.put(movie.getId().toString(), movie.getTitle());
	        		return currentMovie;
        		}).collect(Collectors.toList());
    }
}
