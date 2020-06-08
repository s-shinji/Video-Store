package com.example.sampleproject.service;

import java.util.Optional;
import javax.transaction.Transactional;
import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailService {
	@Autowired
	UserDetailMapper userDetailMapper;

	public Optional<Movie> findPopularMovie(int user_id) {
		return userDetailMapper.findPopularMovie(user_id);
	};

	public Optional<MemberRegistrationEntity> findUserNameById(int user_id) {
		return userDetailMapper.findUserNameById(user_id);
	};

	public void avatarUpdate(String avatar, int id) {
		if (userDetailMapper.avatarUpdate(avatar, id) == 0) {
			throw new MovieNotFoundException("更新するユーザーが見つかりません");
		}
	};
	
}

