package com.example.sampleproject.service;

import java.util.List;

import com.example.sampleproject.entity.Review;
import com.example.sampleproject.mapper.ReviewMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	@Autowired
	ReviewMapper reviewMapper;

	public void insertReview(Review review) {
		reviewMapper.insertReview(review);
	}

	public List<Review> findReviewById(int movie_id) {
		return reviewMapper.findReviewById(movie_id);
	}

	public void deleteReview(int user_id) {
		reviewMapper.deleteReview(user_id);
	}

	public String findMatchUserId(int user_id) {
		return reviewMapper.findMatchUserId(user_id);
	}
}