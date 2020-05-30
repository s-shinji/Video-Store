package com.example.sampleproject.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.mapper.UserDetailMapper;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailService {
	@Autowired
	UserDetailMapper userDetailMapper;

	public Optional<Movie> findPopularMovie(int user_id) {
		// try {
		return userDetailMapper.findPopularMovie(user_id);
		// } catch (EmptyResultDataAccessException e) {
		// 	throw new MovieNotFoundException("指定されたユーザーの動画が見つかりません");
		// }

	};

	public Optional<MemberRegistrationEntity> findUserNameById(int user_id) {
		// try {
		return userDetailMapper.findUserNameById(user_id);
		// } catch (EmptyResultDataAccessException e) {
		// 	throw new MovieNotFoundException("指定されたユーザーの動画が見つかりません");
		// }

	};

	public void avatarUpdate(String avatar, int id) {
		// userDetailMapper.avatarUpdate(avatar);
		if (userDetailMapper.avatarUpdate(avatar, id) == 0) {
			throw new MovieNotFoundException("更新するユーザーが見つかりません");
		}
	};
	
}

