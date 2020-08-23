package com.example.sampleproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
// import java.util.HashMap;
import java.util.List;
// import java.util.Map;
import java.util.Optional;

// import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.form.ProfileForm;
import com.example.sampleproject.service.FollowService;
import com.example.sampleproject.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @Controller
@RestController
@RequestMapping("/user")
public class ProfileController {
	private final ProfileService profileService;
	private final FollowService  followService;

	@Autowired
	public ProfileController(ProfileService profileService, FollowService  followService) {
		this.profileService = profileService;
		this.followService  = followService;
	}
	
	@GetMapping("/{id}")
	// @ResponseBody
	public List<Object> user(@PathVariable("id") int userId, @RequestParam int loginUserId) {
		List<Object> userAllInfo = new ArrayList<>();
		//最も再生回数が多い動画を一つ取得
		Optional<Movie> popularMovieOpt = profileService.findPopularMovie(userId);
		if (popularMovieOpt.isPresent()) {
			Movie popularMovie = popularMovieOpt.get();
			StringBuffer data  = new StringBuffer();
			String base64_4    = Base64.getEncoder().encodeToString(popularMovie.getMovie());
			data.append("data:video/mp4;base64,");
			data.append(base64_4);
			// model.addAttribute("convert", data.toString());
			// model.addAttribute("popularMovie", popularMovie);	
			userAllInfo.add(data.toString());
			userAllInfo.add(popularMovie);

		} else {
			Movie popularMovie = null;
			// model.addAttribute("popularMovie", popularMovie);
			StringBuffer data  = new StringBuffer();
			userAllInfo.add(data.toString());
			userAllInfo.add(popularMovie);
		}
		//ユーザーネームの取得
		Optional<MemberRegistrationEntity> userNameOpt = profileService.findUserNameById(userId);
		if (userNameOpt.isPresent()) {
			MemberRegistrationEntity userName = userNameOpt.get();
			// model.addAttribute("userName", userName);
			userAllInfo.add(userName);
		} else {
			MemberRegistrationEntity userName = null;
			// model.addAttribute("userName", userName);
			userAllInfo.add(userName);
		}

		//フォローしているかどうかで条件分岐する(フォローを探すメソッドを作成しそれが空か否かでtrue or falseを選択する)
		if(followService.checkFollow(userId, loginUserId) == 0) {
			// model.addAttribute("isFollowed", false);
			Boolean isFollowed = false;
			userAllInfo.add(isFollowed);
		} else {
			// model.addAttribute("isFollowed", true);
			Boolean isFollowed = true;
			userAllInfo.add(isFollowed);

		}
		//フォローの際に使用するためユーザーIDを渡しておく
		// model.addAttribute("userId", userId);
		userAllInfo.add(userId);


		//自分がフォローしているユーザーが存在するかを確認し、その情報を取得する
		if(followService.findFollowingById(userId).size() == 0) {
		} else {
			List<Integer> followingUserIdList  = followService.findFollowingById(userId);
			List<MemberRegistrationEntity> followingUserInfoList = new ArrayList<>();
			for(int followingUserId : followingUserIdList) {
				followingUserInfoList.add(profileService.findFollowingUserInfo(followingUserId));
			}
			// model.addAttribute("followingUserInfoList", followingUserInfoList);
			userAllInfo.add(followingUserInfoList);

		}

		//自分がフォローされているユーザーが存在するかを確認し、その情報を取得する
		if(followService.findFollowerById(userId).size() == 0) {
		} else {
			List<Integer> followerUserIdList  = followService.findFollowerById(userId);
			List<MemberRegistrationEntity> followerUserInfoList = new ArrayList<>();
			for(int followerUserId : followerUserIdList) {
				followerUserInfoList.add(profileService.findFollowerUserInfo(followerUserId));
			}
			// model.addAttribute("followerUserInfoList", followerUserInfoList);
			userAllInfo.add(followerUserInfoList);
		}
 
		// return "user";
		return userAllInfo;
	}

	@PostMapping("/{id}")
	public Object avatar(@Validated ProfileForm profileForm, @PathVariable("id") int userId, @RequestParam("avatar") MultipartFile avatarParams) throws IOException {
		// 	//hiddenタグが書き換えられた場合のエラーハンドリング
		// 	if(!(userId == loginUserId)) {
		// 		redirectAttributes.addFlashAttribute("error", "ユーザーが一致しません");
		// 		return "redirect:/user/" + loginUserId;
		// 	}

		//ファイルの選択なしに更新ボタンを押された場合のエラーハンドリング
		// MultipartFile avatarUpdate = profileForm.getAvatar();
		// if(avatarUpdate.isEmpty()) {
			// redirectAttributes.addFlashAttribute("fileError", "ファイルを選択してください");
			// return "redirect:/user/" + loginUserId;
		// }

		//選択されたファイルのbyte型へのエンコードおよびString型へのエンコード
		StringBuffer data = new StringBuffer();
		String base64_5   = Base64.getEncoder().encodeToString(profileForm.getAvatar().getBytes());
		data.append("data:image/jpeg;base64,");
		data.append(base64_5);
		profileService.avatarUpdate(data.toString(), userId);
		// redirectAttributes.addFlashAttribute("complete", "更新が完了しました！");

		return "更新が完了しました!";

		//JSON形式で返す準備
		// Map<Object,Object> avatarMap = new HashMap<Object, Object>();
		// avatarMap.put("avatarData", data.toString());
		// Optional<MemberRegistrationEntity> userNameOpt = profileService.findUserNameById(userId);
		// if (userNameOpt.isPresent()) {
		// 	Object userName = userNameOpt.get();
		// 	// model.addAttribute("userName", userName);
		// 	avatarMap.put("avatar",userName);
		// }
		// return avatarList;

	}


}