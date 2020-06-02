package com.example.sampleproject.entity;

// import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;

// @Entity
public class Image {

	private int id;
	private String image;
	private int movie_id;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "movie_id")
	// private Movie movie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
}