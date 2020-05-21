package com.example.sampleproject.service;

import java.util.List;

import com.example.sampleproject.entity.Movie;

public interface MovieService {
	//insertなどでも問題ないが一般的にsaveが用いられる
	void save(Movie movie);

	List<Movie> getAll();

	void deleteById(int id);

}