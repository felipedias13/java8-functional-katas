package katas;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Combine videos and bookmarks by index (StreamUtils.zip) (https://github.com/poetix/protonpack)
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("videoId", "5", "bookmarkId", "3")
*/
public class Kata8 {
    public static List<Map> execute() {
        List<Movie> movies = DataUtil.getMovies();
        List<Bookmark> bookMarks = DataUtil.getBookMarks();
        
        Stream<Movie> streamMovies = movies.stream();
        Stream<Bookmark> streamBookmarks = bookMarks.stream();

        return StreamUtils.zip(streamMovies, streamBookmarks, 
        		(a,b) -> ImmutableMap.of("videoId", a.getId(), "bookmarId", b.getId())).collect(Collectors.toList());

    }
    

}