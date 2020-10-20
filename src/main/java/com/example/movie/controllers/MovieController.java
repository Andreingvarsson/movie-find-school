package com.example.movie.controllers;

import com.example.movie.dto.MovieDto;
import com.example.movie.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // controller with requestBody - returns Jsonobject.
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<MovieDto> findMovies(@RequestParam String title){
        var movie = movieService.search(title);
        return ResponseEntity.ok(movie);
    }
}
