package com.Movie.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Movie.Entity.Movie;
import com.Movie.Repository.MovieRepository;

public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping(value = "/Movies")
    public ResponseEntity<Map> Movies() {
        Map data = new HashMap<>();
        List<Movie> movie = movieRepository.findAll();
        data.put("data", movie);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/Movies/{id}")
    public ResponseEntity<Map> Moviesid(@PathVariable Integer id) {
        Map data = new HashMap<>();

        if(!movieRepository.existsById(id)){
            data.put("icon", "error");
            data.put("message", "Data Tidak Ditemukan");
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }

        Movie movie = movieRepository.findById(id).get();
        data.put("data", movie);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    
    @PostMapping(value = "/Movies")
    public ResponseEntity<Map> InputMovie(@RequestParam String title, @RequestParam String description, @RequestParam Float rating, @RequestParam String image) {
        Map data = new HashMap<>();

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setRating(rating);
        movie.setImage(image);
        movieRepository.save(movie);

        data.put("icon", "success");
        data.put("message", "Sukses Post Movie");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value = "/Movies/{id}")
    public ResponseEntity<Map> updateMovie(@PathVariable(required = true) Integer id,@RequestParam(required = false) String title, @RequestParam(required = false) String description, @RequestParam(required = false) Float rating, @RequestParam(required = false) String image) {
        Map data = new HashMap<>();

        if(!movieRepository.existsById(id)){
            data.put("icon", "error");
            data.put("message", "Data Tidak Ditemukan");
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }

        Movie movie = movieRepository.getById(id);
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setRating(rating);
        movie.setImage(image);
        // movie.setTitle(movie.getTitle());
        // movie.setDescription(movie.getDescription());
        // movie.setRating(movie.getRating());
        // movie.setImage(movie.getImage());
        movieRepository.save(movie);

        data.put("icon", "success");
        data.put("message", "Sukses Update Movie");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Movies/{id}")
    public ResponseEntity<Map> deleteMovie(@PathVariable(required = true) Integer id) {
        Map data = new HashMap<>();

        if(!movieRepository.existsById(id)){
            data.put("icon", "error");
            data.put("message", "Data Tidak Ditemukan");
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }

        movieRepository.deleteById(id);

        data.put("icon", "success");
        data.put("message", "Sukses Delete Movie");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
}
