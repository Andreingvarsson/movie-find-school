package com.example.movie.repositories;

import com.example.movie.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// for db connection

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> { // extends mongoRepo<type of object and what type of datatype>



}
