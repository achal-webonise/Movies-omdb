package com.example.movies_omdb;

public class Movie {

    private String title , poster , overview,rating;

    public Movie(String title , String poster , String overview , String rating){
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getRating() {
        return rating;
    }
}