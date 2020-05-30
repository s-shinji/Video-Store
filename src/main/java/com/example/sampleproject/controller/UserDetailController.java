package com.example.sampleproject.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.form.UserDetailForm;
import com.example.sampleproject.service.UserDetailService;

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
public class UserDetailController {
	private final UserDetailService userDetailService;

	@Autowired
	public UserDetailController(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}
	
	// @ModelAttribute
    // public UserDetailForm setForm() {
    //     return new UserDetailForm();
	// }
	
	@GetMapping("/{id}")
	public String user(@PathVariable int id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof DbUserDetails){
			int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			model.addAttribute("loginUser", userId);

			// if(!(id == userId)) {
			// 	return "redirect:/index";
			// }
	
			Optional<Movie> popularMovieOpt = userDetailService.findPopularMovie(id);
			if (popularMovieOpt.isPresent()) {
				Movie popularMovie = popularMovieOpt.get();
				StringBuffer data = new StringBuffer();
				String base64_4 = Base64.getEncoder().encodeToString(popularMovie.getMovie());
				data.append("data:video/mp4;base64,");
				data.append(base64_4);
				model.addAttribute("convert", data.toString());
				model.addAttribute("popularMovie", popularMovie);	
			} else {
				Movie popularMovie = null;
				model.addAttribute("popularMovie", popularMovie);	
			}

			Optional<MemberRegistrationEntity> userNameOpt = userDetailService.findUserNameById(id);
			if (userNameOpt.isPresent()) {
				MemberRegistrationEntity userName = userNameOpt.get();
				model.addAttribute("userName", userName);
			} else {
				MemberRegistrationEntity userName = null;
				model.addAttribute("userName", userName);

			}
	
		}

		return "user";
	}

	@PostMapping("/{id}")
	public String avatar(@Validated UserDetailForm userDetailForm,BindingResult result, @PathVariable int id, Model model, RedirectAttributes redirectAttributes) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getPrincipal() instanceof DbUserDetails){
			int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			model.addAttribute("loginUser", userId);

			if(!(id == userId)) {
				redirectAttributes.addFlashAttribute("error", "ユーザーが一致しません");
				return "redirect:/user/" + userId;
			}

			MultipartFile avatarUpdate = userDetailForm.getAvatar();
			if(avatarUpdate.isEmpty()) {
				redirectAttributes.addFlashAttribute("fileError", "ファイルを選択してください");
				return "redirect:/user/" + userId;
			}

			StringBuffer data = new StringBuffer();
			String base64_5 = Base64.getEncoder().encodeToString(userDetailForm.getAvatar().getBytes());
			data.append("data:image/jpeg;base64,");
			data.append(base64_5);
	
			userDetailService.avatarUpdate(data.toString(), id);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました！");

		}
		return "redirect:/user/{id}";

	}


}