package katas;

import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.InterestingMoment;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        
        return movieLists.stream()
        		.flatMap(c -> c.getVideos().stream())
        		.map(movie -> ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(),
        				"time", getMiddleMoment(movie.getInterestingMoments()), "uri", getSmallestBoxArt(movie.getBoxarts()))).collect(Collectors.toList());
    }
    
    public static Date getMiddleMoment(List<InterestingMoment> interestingMoments){
    	Optional<Date> interestingMoment = interestingMoments.stream()
    			.filter(moment -> moment.getType().equals("Middle"))
    			.map(InterestingMoment::getTime).findAny();
    	if(interestingMoment.isPresent())
    		return interestingMoment.get();
    	else return new Date();
    }
    
    public static String getSmallestBoxArt(List<BoxArt> boxArts){
    	Optional<BoxArt> url = boxArts.stream()
    			.reduce((a,b) -> a.getHeight()*a.getWidth() < b.getHeight()*b.getWidth() ? a : b);
    	if(url.isPresent())
    		return url.get().getUrl();
    	else return "";
    }
}
