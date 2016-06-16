package com.medha.imdb;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prathyusha on 2/25/16.
 */
public class Movie implements Parcelable {

    String title,year,imdbID,poster,released=null,genre=null,director=null,actors=null,plot=null;
    float imdbRating;

    protected Movie(Parcel in) {
        title = in.readString();
        year = in.readString();
        imdbID = in.readString();
        poster = in.readString();
        released = in.readString();
        genre = in.readString();
        director = in.readString();
        actors = in.readString();
        plot = in.readString();
        imdbRating = Float.parseFloat(in.readString());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(){
        super();
    }





    static  Movie createMovie(JSONObject js) throws JSONException {
         Movie movie = new Movie();
         movie.setTitle(js.getString("Title"));
         movie.setYear(js.getString("Year"));
         movie.setImdbID(js.getString("imdbID"));
         movie.setPoster(js.getString("Poster"));
         return movie;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(imdbID);
        dest.writeString(poster);
        dest.writeString(released);
        dest.writeString(genre);
        dest.writeString(director);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeString(String.valueOf(imdbRating));
    }
}
