package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.Optional;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();
        
        Optional<String> boxArt = movies.stream()
        		.flatMap(a -> a.getBoxarts().stream())
        		.reduce((a,b) -> a.getHeight()*a.getWidth() > b.getHeight()*b.getWidth() ? a : b)
        		.map(BoxArt::getUrl);
        
        
        if(boxArt.isPresent())
        	return boxArt.get();
        else return "";
    }
}