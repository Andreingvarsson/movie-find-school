package com.example.movie.controllers;

import com.example.movie.entities.Movie;
import com.example.movie.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController // controller with requestBody - returns Jsonobject.
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> findMovies(@RequestParam String title){
        var movie = movieService.findAll(title);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovieByID(@PathVariable String id){
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)       // be clear with what we send back is JSON.
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){       // responseentity is a httpresponse.
        var savedMovie = movieService.save(movie);
        var uri = URI.create("/api/v1/movies/" + savedMovie.getId());   // /api/v1/movies/1324151515
        return ResponseEntity.created(uri).body(savedMovie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovie(@PathVariable String id, @RequestBody Movie movie){
        movieService.update(id, movie);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable String id){
        movieService.delete(id);
    }

}
