package com.medha.imdb;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/25/16.
 */
public class ParseMovieDetails {
    static class parseDetails{
        //static ArrayList<Movie> movies;
        static Movie movie;
        static Movie returnMovies(String in) throws JSONException {
            movie = new Movie();
            JSONObject root = new JSONObject(in);
            movie.setGenre(root.getString("Genre"));
            movie.setReleased(root.getString("Released"));
            movie.setDirector(root.getString("Director"));
            movie.setActors(root.getString("Actors"));
            movie.setImdbRating(root.getLong("imdbRating"));
            movie.setPlot(root.getString("Plot"));
            movie.setPoster(root.getString("Poster"));
            movie.setTitle(root.getString("Title"));
            return movie;
        }
    }
}
