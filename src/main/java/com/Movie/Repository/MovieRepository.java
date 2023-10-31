package com.Movie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Movie.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
}
