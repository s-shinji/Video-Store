package com.example.sampleproject.mapper;

import java.util.Optional;

import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.entity.Movie;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileMapper {
	public Optional<Movie> findPopularMovie(int user_id);

	public Optional<MemberRegistrationEntity> findUserNameById(int user_id);

	public int avatarUpdate(String avatar, int id);

	public MemberRegistrationEntity findFollowingUserInfo(int follower_id);

	public MemberRegistrationEntity findFollowerUserInfo(int followee_id);

}