package com.example.sampleproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.form.MemberRegistrationForm;
import com.example.sampleproject.service.RegisterMemberService;

// @Controller
@RestController
//springとreactでurlがかぶらないようにするため追加
@RequestMapping("/KdiJ362")
public class MemberRegistrationController {

	@Autowired
	private RegisterMemberService registMemberService;
	@Autowired
	private MemberRegistrationEntity entity;

	/**
	 * 会員情報入力画面に遷移する。
	 */
	// @RequestMapping("/RegistrationForm")
	// public String showRegistMemberForm(Model model) {

	// 	model.addAttribute(new MemberRegistrationForm());

	// 	//会員情報入力画面。
	// 	return "RegistrationForm";
	// }

	@PostMapping("/Register")
	public int registerUser(@Validated @ModelAttribute MemberRegistrationForm memberRegistrationForm,
								BindingResult result,
								@RequestParam("name") String name,
								@RequestParam("email") String email, 
								@RequestParam("password") String password,
								@RequestParam("passwordConfirmation") String passwordConfirmation) {
        if(result.hasErrors()) {
			//ユーザー名とメールアドレスの重複の場合
			// if("すでに登録済みのメールアドレスです".equals(result.getAllErrors().get(0).getDefaultMessage()) &&
			//    "すでに登録済みのユーザー名です".equals(result.getAllErrors().get(1).getDefaultMessage())) {
			// 	return 1;
			// }

			//ユーザー名の重複の場合
			if("すでに登録済みのユーザー名です".equals(result.getAllErrors().get(0).getDefaultMessage())) {
				return 2;
			}
			//メールアドレスの重複の場合
			if("すでに登録済みのメールアドレスです".equals(result.getAllErrors().get(0).getDefaultMessage())) {
				return 3;
			}

		//     return "RegistrationForm";
		}

		//パスワード不一致の場合
		if (!(passwordConfirmation.equals(password))) {
		// 	result.rejectValue("passwordConfirmation",null, "パスワードが一致してません。");
		//     return "RegistrationForm";
			return 4;
		}

		//USERテーブルにinsertする時の引数。
		// MemberRegistrationEntity entity = new MemberRegistrationEntity();

		entity.setName(name);
		entity.setEmail(email);
		entity.setPassword(password);
		// entity.setAvatar("/images/default.jpeg");

		//USERテーブルにinsertする。
		registMemberService.registerMember(entity);

		return 0;
	}

}