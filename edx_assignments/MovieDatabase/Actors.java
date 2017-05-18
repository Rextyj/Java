
import java.util.*;

public class Actors {
    private String name;
    private ArrayList<Movie> movies;
    
    public Actors() {
	name = "";
	movies = new ArrayList<Movie>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Movie movies) {
        this.movies.add(movies);
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
