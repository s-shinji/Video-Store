package com.example.sampleproject.mapper;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.sampleproject.entity.Notification;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper {
	public int searchLatestNotificationInfo(int movie_id, int followee_id, int follower_id, LocalDateTime created);

	public int updateLatestNotificationInfo(int movie_id, int followee_id, int follower_id, LocalDateTime created);

	public void insertLatestNotificationInfo(int movie_id, int followee_id, int follower_id, LocalDateTime created);

	public Optional<Notification> getLatestCreated(int followee_id, int follower_id);

	public void deleteNotification(int followee_id, int follower_id);
}