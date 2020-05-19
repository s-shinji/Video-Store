package com.example.sampleproject.dao;

import java.util.List;

import com.example.sampleproject.entity.Movie;

// import org.apache.ibatis.annotations.Mapper;
// @Mapper
public interface MovieDao {

	void insertMovie(Movie movie);

	List<Movie> getAll();

    // int deleteById(int id);
	
}