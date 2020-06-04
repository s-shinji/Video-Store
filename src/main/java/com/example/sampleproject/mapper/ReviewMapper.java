package com.example.sampleproject.mapper;

import java.util.List;

import com.example.sampleproject.entity.Review;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
	public void insertReview(Review review);

	public List<Review> findReviewById(int movie_id);

	public int deleteReview(int movie_id, int user_id);

	public String findMatchUserId(int movie_id, int user_id);
}