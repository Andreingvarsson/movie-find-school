package com.example.movie.services;

import com.example.movie.entities.Movie;
import com.example.movie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieConsumerService movieConsumerService;

    @Cacheable(value = "movieCache", key = "#title")
    public List<Movie> findAll(String title) {
       // return movieRepository.findAll();   // doesnt find anything? returns empty.
        var movies = movieRepository.findAll();
        System.out.println("Fresh data...");
        movies = movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        if(movies.isEmpty()){
            var moviesDto = movieConsumerService.search(title);
            if(moviesDto != null){
                var movie = new Movie(moviesDto.getTitle(), moviesDto.getPlot(), moviesDto.getLanguage(),
                        moviesDto.getCountry(), moviesDto.getYear(), moviesDto.getImdbID(), moviesDto.getActors());
                movies.add(this.save(movie));
            }
        }
        return movies;
    }

    public Movie findById(String id) {

        /*var movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "kunde ej hitta filmen");
        }
        return movie.get();
         */

        // Same result as the code above.
        return movieRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde inte hitta filmen"));

    }


   // @CacheEvict(value = "movieCache", key = "#result.id", allEntries = true)
    @CachePut(value = "movieCache", key = "#result.id")
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @CachePut(value = "movieCache", key = "id")
    public void update(String id, Movie movie) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "kunde ej hitta filmen");
        }
        movie.setId(id);
        movieRepository.save(movie);
    }

    @CacheEvict(value = "movieCache", allEntries = true)
    public void delete(String id){
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "kunde ej hitta filmen");
        }
        movieRepository.deleteById(id);
    }
}


