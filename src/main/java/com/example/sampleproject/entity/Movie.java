package com.example.sampleproject.entity;
import java.time.LocalDateTime;

public class Movie {
	private int id;
	private String movie;
	private LocalDateTime created;

	public Movie() {

	}; 

	// public Movie(int id, String movie, LocalDateTime created){
	// 	this.id    = id;
	// 	this.movie = movie;
	// 	this.created = created;

	// };

	public int getId(){
		return id;
	}

	public String getMovie() {
		return movie;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
}