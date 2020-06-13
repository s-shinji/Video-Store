package com.example.sampleproject.mapper;

import com.example.sampleproject.entity.Movie;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {
	public Movie getFollowingUserLatestMovie(int followee_id);
}