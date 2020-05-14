package com.example.sampleproject.dao;

import java.util.List;

import com.example.sampleproject.entity.Movie;

public interface MovieDao {

	void insertMovie(Movie movie);

	List<Movie> getAll();
	
}