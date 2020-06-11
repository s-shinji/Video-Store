package com.example.sampleproject.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.sampleproject.mapper.FollowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FollowService {
	
	@Autowired
	FollowMapper followMapper;

	public void save(int followee_id, int follower_id) {
		followMapper.insertFollow(followee_id, follower_id);
	}

	public void deleteFollow(int followee_id, int follower_id) {
		followMapper.deleteFollow(followee_id, follower_id);
	}

	public int checkFollow(int followee_id, int follower_id) {
		return followMapper.checkFollow(followee_id, follower_id);
	}

	public List<Integer> findFollowingById(int follower_id) {
		return followMapper.findFollowingById(follower_id);
	}

	public List<Integer> findFollowerById(int followee_id) {
		return followMapper.findFollowerById(followee_id);
	}
}