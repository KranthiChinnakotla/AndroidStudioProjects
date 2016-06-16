package com.medha.imdb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/25/16.
 */
public class ParseMovies {
    static class JsonMovies{
        static ArrayList<Movie> movies;
        static ArrayList<Movie> jsonParseMovies(String in) throws JSONException {
            JSONObject root = new JSONObject(in);
            JSONArray srchArray = root.getJSONArray("Search");
            Movie movie = new Movie();
            movies = new ArrayList<Movie>();
            for (int i =0; i<srchArray.length();i++){

                JSONObject movieDetails =  srchArray.getJSONObject(i);
                movie =  Movie.createMovie(movieDetails);
                movies.add(movie);

            }


            return movies;
        }
    }
}
