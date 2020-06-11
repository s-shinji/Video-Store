package com.example.sampleproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {
	public void insertFollow(int followee_id, int follower_id);

	public void deleteFollow(int followee_id, int follower_id);

	public int checkFollow(int followee_id, int follower_id); 

	public List<Integer> findFollowingById(int follower_id);

	public List<Integer> findFollowerById(int followee_id);
}