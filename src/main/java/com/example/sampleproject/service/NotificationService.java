package com.example.sampleproject.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.sampleproject.entity.Notification;
import com.example.sampleproject.mapper.NotificationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NotificationService {
	@Autowired
	NotificationMapper notificationMapper;

	public int searchLatestNotificationInfo(Notification notification) {
		//動画、フォロー、フォロワー全てが一致するものが既に存在する場合は1を返す
		if(notificationMapper.searchLatestNotificationInfo(notification) == 1) {
			return notificationMapper.searchLatestNotificationInfo(notification);
		} else {

			//全く情報が存在せず、updateで0が帰ってきた場合は新たに情報を追加して0を返す
			if(notificationMapper.updateLatestNotificationInfo(notification) == 0) {
				notificationMapper.insertLatestNotificationInfo(notification);
				return 0;

			// 今回送られてきたmovieテーブルからの情報が以前notificationに保存されていた作成日よりも前の場合1を返す（フォローユーザーの最新動画が削除された場合に起こる）
			//ここで処理を行うと送られてきた情報にnotificationの値が上書きされてしまう？
			// } else if(created.compareTo(notificationMapper.getLatestCreated(followee_id, follower_id).getCreated()) == 0){
			// 	return 1;

			//フォロー、フォロワー情報は存在するが動画が最新でない場合、updateして0を返す
			} else {
				return 0;
			}
			
		}
	}

	public Optional<Notification> getLatestCreated(int followee_id, int follower_id) {
		return notificationMapper.getLatestCreated(followee_id, follower_id);
	};

	public void deleteNotification(int followee_id, int follower_id) {
		notificationMapper.deleteNotification(followee_id, follower_id);
	}
}