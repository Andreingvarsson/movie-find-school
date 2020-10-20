package com.example.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// DTO = data transfer Object

public class MovieDto {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Year")
    private String year;
    private String imdbID;
   @JsonProperty("Actors")
    private String actors;

   private String test;

    public MovieDto() {}

    public MovieDto(String title, String plot, String language, String country, String year, String imdbID, String actors) {
        this.title = title;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.year = year;
        this.imdbID = imdbID;
        this.actors = actors;
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
        return List.of(actors.split(", "));
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
