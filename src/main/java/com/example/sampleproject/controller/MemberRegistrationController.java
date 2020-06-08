package com.example.sampleproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.form.MemberRegistrationForm;
import com.example.sampleproject.service.RegisterMemberService;

@Controller
public class MemberRegistrationController {

	@Autowired
	private RegisterMemberService registMemberService;

	/**
	 * 会員情報入力画面に遷移する。
	 */
	@RequestMapping("/RegistrationForm")
	public String showRegistMemberForm(Model model) {

		model.addAttribute(new MemberRegistrationForm());

		//会員情報入力画面。
		return "RegistrationForm";
	}

	@RequestMapping("/Register")
	public String registerUser(@Validated @ModelAttribute MemberRegistrationForm memberRegistrationForm,
								BindingResult result) {
        if(result.hasErrors()) {
            return "RegistrationForm";
		}
		
		if (!(memberRegistrationForm.getPasswordConfirmation().equals(memberRegistrationForm.getPassword()))) {
			result.rejectValue("passwordConfirmation",null, "パスワードが一致してません。");
            return "RegistrationForm";
		}

		//USERテーブルにinsertする時の引数。
		MemberRegistrationEntity entity = new MemberRegistrationEntity();

		entity.setName(memberRegistrationForm.getName());
		entity.setEmail(memberRegistrationForm.getEmail());
		entity.setPassword(memberRegistrationForm.getPassword());
		entity.setAvatar("/images/default.jpeg");

		//USERテーブルにinsertする。
		registMemberService.registerMember(entity);

		return "Result";
	}
}