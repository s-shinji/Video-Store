package com.example.sampleproject.controller;

import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.service.FollowService;
import com.example.sampleproject.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class FollowController {
	@Autowired
	FollowService followService;

	@Autowired
	NotificationService notificationService;
	
	@PostMapping("/follow/{id}")
	//メソッドをvoidにするかStringにするか（ajaxが戻り値なしでいけるか、modelがうまく働くか、エラーハンドリングをした場合の問題）
	public Boolean follow( @PathVariable("id") int userId, Model model) {
		int loginUserId = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof DbUserDetails){
			loginUserId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			
			followService.save(userId, loginUserId);
		}
		//ajaxで戻り値が必要なため返している
		return true;
	}

	@PostMapping("/followed/{id}")
	public Boolean followed( @PathVariable("id") int userId, Model model) {
		int loginUserId = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof DbUserDetails){
			loginUserId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			
			followService.deleteFollow(userId, loginUserId);
			//フォロー解除の際にnotificationのデータを削除する
			notificationService.deleteNotification(userId, loginUserId);
		}
		return true;
	} 
}