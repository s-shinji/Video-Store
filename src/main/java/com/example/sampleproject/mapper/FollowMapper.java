package com.example.sampleproject.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {
	public void insertFollow(int followee_id, int follower_id);

	public void deleteFollow(int followee_id, int follower_id);

	public int checkFollow(int followee_id, int follower_id); 
}