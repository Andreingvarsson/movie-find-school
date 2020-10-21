package com.example.movie.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private static final long serialVersionUID = 669636611022282531L;

    @Id
    private String id;
    private String title;
    private String plot;
    private String language;
    private String country;
    private String year;
    @Indexed(unique = true) // makes imdbID unique, wont add more of the same.
    private String imdbID;
    private List<String> actors;


    public Movie() {
    }

    public Movie(String title, String plot, String language, String country, String year, String imdbID, List<String> actors) {
        this.title = title;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.year = year;
        this.imdbID = imdbID;
        this.actors = actors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
