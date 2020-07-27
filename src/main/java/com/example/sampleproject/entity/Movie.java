package com.example.sampleproject.entity;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Movie {
    private int id;
    private byte[] movie;
    private LocalDateTime created;
    private int userId;
    private MemberRegistrationEntity user;
    private int views;
    private String title;
    private Image image;
    private Review review;

    public Movie() {
    };

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public MemberRegistrationEntity getUser() {
    return user;
    }

    public void setUser(MemberRegistrationEntity user) {
    this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public byte[] getMovie() {
        return movie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovie(byte[] movie) {
        this.movie = movie;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    
}