package com.example.sampleproject.entity;

import org.springframework.stereotype.Component;

@Component
public class Review {
	private int id;
	private String review;
	private int movie_id;
	private int user_id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}