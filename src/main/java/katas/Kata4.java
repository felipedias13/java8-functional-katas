package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
        		.flatMap(movieList -> movieList.getVideos().stream())
        		.map(movie -> {
        			Map<String, String> currentMovie = new HashMap<String, String>();
        			currentMovie.put("id", movie.getId().toString());
        			currentMovie.put("title", movie.getTitle());
        			currentMovie.put("boxart", movie.getBoxarts().toString());
        			return currentMovie;
        		}).collect(Collectors.toList());
    }
}
