package com.example.sampleproject.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.form.ProfileForm;
import com.example.sampleproject.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class ProfileController {
	private final ProfileService profileService;

	@Autowired
	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	// @ModelAttribute
    // public ProfileForm setForm() {
    //     return new ProfileForm();
	// }
	
	@GetMapping("/{id}")
	public String user(@PathVariable("id") int userId, Model model) {
		//ログインユーザーを取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int loginUserId               = 0;
	    if(authentication.getPrincipal() instanceof DbUserDetails){
			loginUserId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			model.addAttribute("loginUser", loginUserId);
	
		}
		//最も再生回数が多い動画を一つ取得
		Optional<Movie> popularMovieOpt = profileService.findPopularMovie(userId);
		if (popularMovieOpt.isPresent()) {
			Movie popularMovie = popularMovieOpt.get();
			StringBuffer data  = new StringBuffer();
			String base64_4    = Base64.getEncoder().encodeToString(popularMovie.getMovie());
			data.append("data:video/mp4;base64,");
			data.append(base64_4);
			model.addAttribute("convert", data.toString());
			model.addAttribute("popularMovie", popularMovie);	
		} else {
			Movie popularMovie = null;
			model.addAttribute("popularMovie", popularMovie);	
		}
		//ユーザーネームの取得
		Optional<MemberRegistrationEntity> userNameOpt = profileService.findUserNameById(userId);
		if (userNameOpt.isPresent()) {
			MemberRegistrationEntity userName = userNameOpt.get();
			model.addAttribute("userName", userName);
		} else {
			MemberRegistrationEntity userName = null;
			model.addAttribute("userName", userName);

		}
		return "user";
	}

	@PostMapping("/{id}")
	public String avatar(@Validated ProfileForm profileForm,BindingResult result, @PathVariable("id") int userId, Model model, RedirectAttributes redirectAttributes) throws IOException {
		//ログインユーザーを取得
		int loginUserId = 0;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getPrincipal() instanceof DbUserDetails){
			loginUserId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			model.addAttribute("loginUser", loginUserId);

			//hiddenタグが書き換えられた場合のエラーハンドリング
			if(!(userId == loginUserId)) {
				redirectAttributes.addFlashAttribute("error", "ユーザーが一致しません");
				return "redirect:/user/" + loginUserId;
			}
			//ファイルの選択なしに更新ボタンを押された場合のエラーハンドリング
			MultipartFile avatarUpdate = profileForm.getAvatar();
			if(avatarUpdate.isEmpty()) {
				redirectAttributes.addFlashAttribute("fileError", "ファイルを選択してください");
				return "redirect:/user/" + loginUserId;
			}
		}
		//選択されたファイルのbyte型へのエンコードおよびString型へのエンコード
		StringBuffer data = new StringBuffer();
		String base64_5   = Base64.getEncoder().encodeToString(profileForm.getAvatar().getBytes());
		data.append("data:image/jpeg;base64,");
		data.append(base64_5);

		profileService.avatarUpdate(data.toString(), userId);
		redirectAttributes.addFlashAttribute("complete", "更新が完了しました！");

		return "redirect:/user/{id}";

	}


}