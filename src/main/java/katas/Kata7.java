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
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
        		.flatMap(movies -> movies.getVideos().stream())
        		.map(movie -> {
        			return ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "boxart", getSmallestBoxArt(movie.getBoxarts()));
        		}).collect(Collectors.toList());
    }
    
    public static String getSmallestBoxArt(List<BoxArt> boxArts){
    	Optional<BoxArt> url = boxArts.stream()
    			.reduce((a,b) -> a.getHeight()*a.getWidth() < b.getHeight()*b.getWidth() ? a : b);
    	if(url.isPresent())
    		return url.get().getUrl();
    	else return "";
    }
}
