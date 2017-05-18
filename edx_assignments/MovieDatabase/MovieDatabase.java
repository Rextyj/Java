
import java.util.*;
import java.io.*;

public class MovieDatabase {
    
    private ArrayList<Movie> movieList;
    private ArrayList<Actors> actorList;
    
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Actors> getActorList() {
        return actorList;
    }


    //constructor
    public MovieDatabase() {
	movieList = new ArrayList<Movie> ();
	actorList = new ArrayList<Actors> ();
    }
    
    public void addMovie(String name, String[] actors) {
	boolean newEntryM = true;
	boolean newEntryA = true;
 	
	for (Movie inMovieList : movieList) {
	    if (inMovieList.getName().equals(name)) {
		newEntryM = false;
	    }
	}
	
	if (newEntryM) {
	    Movie newMovie = new Movie();
	    newMovie.setName(name);
	    for (String actorName : actors) {
		Actors newActor = new Actors();
		newActor.setName(actorName);
		newActor.setMovies(newMovie);
		newMovie.setActors(newActor);//add a new actor to the current movie
	
		for (Actors inActorList : actorList) {
		    if (inActorList.getName().equals(actorName)) {
			newEntryA = false;
		    }
		}
		//if it is a new actor
		if (newEntryA) {
		    actorList.add(newActor);
		}
	    }
	    //add this obj to list
	    movieList.add(newMovie);
	}
	// if the entry already exists, do nothing
	
    }
    
    public void addRating(String name, double rating) {//assume the name is in the database already
	for (Movie eachMovie : movieList) {
	    if (eachMovie.getName().equals(name)) {
		eachMovie.setRating(rating);
		break;
	    }
	}
    }
    
    public void updateRating(String name, double newRating) {
	for (Movie eachMovie : movieList) {
	    if (eachMovie.getName().equals(name)) {
		eachMovie.setRating(newRating);
		break;
	    }
	}
    }
    
    public String getBestActor() {
	ArrayList<Actors> bestActor = new ArrayList<Actors>();
	double highAverage = 0;
	String bestActorName = "";
	
	for (Actors eachActor : actorList) {
	    double tempRating = 0;
	    double tempAverage;
	    for (Movie eachWork : eachActor.getMovies()) {
		tempRating += eachWork.getRating();
	    }
	    tempAverage = tempRating / eachActor.getMovies().size();
	    if (tempAverage > highAverage) {
		highAverage = tempAverage;
		bestActor.clear();
		bestActor.add(eachActor);
	    } else if(tempAverage == highAverage) {
		bestActor.add(eachActor); 
	    }
	}
	
	for (Actors eachBest : bestActor) {
	    bestActorName += eachBest.getName() + "; ";
	}
	return bestActorName;
    }
    
    public String getBestMovie() {
	ArrayList<Movie> bestMovie = new ArrayList<Movie>();
	double highRating = 0;
	String bestMovieName = "";
	
	for (Movie eachMovie : movieList) {
	    if (eachMovie.getRating() > highRating) {
		highRating = eachMovie.getRating();
		bestMovie.clear();
		bestMovie.add(eachMovie);
	    } else if(eachMovie.getRating() > highRating){
		bestMovie.add(eachMovie);
	    }
	}
	
	for (Movie eachBest : bestMovie) {
	    bestMovieName += eachBest.getName() + "; ";
	}
	return bestMovieName;
    }
    
    public static void main(String[] args) throws IOException {
	MovieDatabase newDatabase = new MovieDatabase();
	File movies = new File("movies.txt");
	Scanner lineReader = new Scanner(movies);
	while (lineReader.hasNextLine()) {
	    String line = lineReader.nextLine();
	    String[] movieName = line.split(", ");
	    String[] actorName = new String[1];
	    actorName[0] = movieName[0];
	    for (int i = 1; i < movieName.length; i++) {
		newDatabase.addMovie(movieName[i], actorName);
	    }
	}
	lineReader.close();
	
	File ratings = new File("ratings.txt");
	Scanner ratingReader = new Scanner(ratings);
	String line = ratingReader.nextLine();
	while (ratingReader.hasNextLine()) {
	    line = ratingReader.nextLine();
//	    System.out.println(line);
	    String ratingPat = "\\s+(\\d+)$"; 
	    String namePat = "^([\\w\\s\\W]+\\s+)";
	    String[] movieRating = line.split(namePat);
	    double rating = Double.parseDouble(movieRating[1]);
	    String[] name = line.split(ratingPat); 
	    newDatabase.addRating(name[0], rating);
	}
	ratingReader.close();
	
//	ArrayList<Movie> movList = newDatabase.getMovieList();
//	for (Movie currentMovie : movList) {
////	    System.out.println(movList.indexOf(currentMovie));
//	    System.out.println(currentMovie.getName());
//	}
	
	System.out.println("The best actor(s) is/are:");
	String bestActor = newDatabase.getBestActor();
	System.out.println(bestActor);
	System.out.println("The best movie(s) is/are:");
	String bestMovie = newDatabase.getBestMovie();
	System.out.println(bestMovie);

    }

}
