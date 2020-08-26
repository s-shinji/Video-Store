// package com.example.sampleproject.mapper;

// import java.util.Optional;

// import com.example.sampleproject.entity.Notification;

// import org.apache.ibatis.annotations.Mapper;

// @Mapper
// public interface NotificationMapper {
// 	public int searchLatestNotificationInfo(Notification notification);

// 	public int updateLatestNotificationInfo(Notification notification);

// 	public void insertLatestNotificationInfo(Notification notification);

// 	public Optional<Notification> getLatestCreated(int followee_id, int follower_id);

// 	public void deleteNotification(int followee_id, int follower_id);
// }