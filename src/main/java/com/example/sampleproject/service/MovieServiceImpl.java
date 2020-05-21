package com.example.sampleproject.service;

import java.util.List;

import com.example.sampleproject.dao.MovieDao;
import com.example.sampleproject.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

	//interface名にしておくことがポイント（こちらはメソッドが確実に保証され変更もないため）
	private final MovieDao dao;

	@Autowired
	public MovieServiceImpl(MovieDao dao) {
		this.dao = dao;
	}
	@Override
	public void save(Movie movie) {
		dao.insertMovie(movie);

	}

	@Override
	public List<Movie> getAll() {
		return dao.getAll();
	}

	@Override
	public void deleteById(int id) {
		//Movieを削除、idがなければ例外発生
		if(dao.deleteById(id) == 0) {
			throw new MovieNotFoundException("削除する動画が存在しません");
		}
	}
	
}