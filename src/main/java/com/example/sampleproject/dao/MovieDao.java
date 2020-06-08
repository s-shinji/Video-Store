package com.example.sampleproject.dao;

import java.util.List;
import java.util.Optional;

import com.example.sampleproject.entity.Movie;

public interface MovieDao {

	int insertMovie(Movie movie);

	List<Movie> getAll();
	
	int deleteById(int id);
	
	Optional<Movie> getMovie(int id);

	int updateViews(int views, int id);

	List<Movie> findBySearchWordLike(String searchWord);

	Optional<Movie> getUserIdByMovieId(int id);
	
}