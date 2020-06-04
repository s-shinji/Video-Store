package com.example.sampleproject.service;

import java.util.List;
import java.util.Optional;

import com.example.sampleproject.dao.MovieDao;
import com.example.sampleproject.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

	// interface名にしておくことがポイント（こちらはメソッドが確実に保証され変更もないため）
	private final MovieDao dao;

	@Autowired
	public MovieServiceImpl(MovieDao dao) {
		this.dao = dao;
	}

	@Override
	public int save(Movie movie) {
		return dao.insertMovie(movie);

	}

	@Override
	public List<Movie> getAll() {
		return dao.getAll();
	}

	// @Override
	// public List<Movie> getAll2() {
	// 	return dao.getAll2();
	// }

	@Override
	public void deleteById(int id) {
		// Movieを削除、idがなければ例外発生
		if (dao.deleteById(id) == 0) {
			throw new MovieNotFoundException("削除する動画が存在しません");
		}
	}

	@Override
	public Optional<Movie> getMovie(int id) {
		try {
			return dao.getMovie(id);
		} catch (EmptyResultDataAccessException e) {
			throw new MovieNotFoundException("指定された動画が存在しません");
		}

	}

	@Override
	public void updateViews(int views, int id) {
		if (dao.updateViews(views, id) == 0) {
			throw new MovieNotFoundException("再生する動画が存在しません");
		}
	}

	@Override
	public List<Movie> findBySearchWordLike(String searchWord) {
		return dao.findBySearchWordLike(searchWord);
	}

	@Override
	public Optional<Movie> getUserIdByMovieId(int id) {
		try {
			return dao.getUserIdByMovieId(id);
		} catch (EmptyResultDataAccessException e) {
			throw new MovieNotFoundException("指定された動画が存在しません");
		}
	}

	// @Override
	// public Movie findReviewById(int id) {
	// 	return dao.findReviewById(id);
	// }
	
}