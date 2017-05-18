
import java.util.*;

public class Movie {
    private String name;
    private ArrayList<Actors> actors;
    private double rating;
    
    public Movie() {
	name = "";
	actors = new ArrayList<Actors> ();
	rating = 0;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }



    public ArrayList<Actors> getActors() {
        return actors;
    }



    public void setActors(Actors actors) {
        this.actors.add(actors);
    }



    public double getRating() {
        return rating;
    }



    public void setRating(double rating) {
        this.rating = rating;
    }



    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
