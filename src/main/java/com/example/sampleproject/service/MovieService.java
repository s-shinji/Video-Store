package com.example.sampleproject.service;

import java.util.List;
import java.util.Optional;

import com.example.sampleproject.entity.Movie;

public interface MovieService {
	//insertなどでも問題ないが一般的にsaveが用いられる
	void save(Movie movie);

	List<Movie> getAll();

	List<Movie> getAll2();

	void deleteById(int id);

	Optional<Movie> getMovie(int id);

	void updateViews(int views, int id);

	List<Movie> findBySearchWordLike(String searchWord);
}