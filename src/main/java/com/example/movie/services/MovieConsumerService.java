package com.example.movie.services;

import com.example.movie.dto.MovieDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Service // tell intelliJ its a service.
@ConfigurationProperties(value = "example.movie", ignoreUnknownFields = false)
public class MovieConsumerService {
    private final RestTemplate restTemplate;
    private String url;

    public MovieConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public MovieDto search(String title){
        var urlWithTitleQuery = url + "&t=" + title;

      var movie = restTemplate.getForObject(urlWithTitleQuery, MovieDto.class);

      // Just showing other examples that should be in their own method. put, post delete
       // restTemplate.getForEntity(URL, Movie.class);
       // restTemplate.postForEntity(URL, new MovieDto(), MovieDto.class); // post
       // restTemplate.put(URL, new MovieDto());
       // restTemplate.delete(URL);

       /* if(movie != null){
            System.out.println("Movie " + movie.getTitle());
        }else{
            System.out.println("No movies found!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no movie found!");
        }
        */

       if(movie == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movie found.");
       }
       movie.setTest("Updated");
        return movie;
    }

    public void setUrl(String url){
        this.url = url;
    }

}
