package katas;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import model.BoxArt;
import model.MovieList;
import util.DataUtil;

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
        			return ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "boxart", getBoxArtUrl(movie.getBoxarts()));
        		}).collect(Collectors.toList());
    }
    
    public static String getBoxArtUrl(List<BoxArt> boxArts){
    	Optional<BoxArt> url = boxArts.stream()
    			.reduce((a,b) -> a.getHeight().equals(200) && a.getWidth().equals(150) ? a : b);
    	if(url.isPresent())
    		return url.get().getUrl();
    	else return "";
    }
}
