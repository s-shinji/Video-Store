package com.example.sampleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/**
	 * ログイン画面 に遷移する。
	 */
	@RequestMapping("/login")
	public String showLoginForm(Model model) {

		//ログイン画面。
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		 model.addAttribute("iserror",true);
		 return "login";
	}
	/**
	 * メインページに遷移する。
	 * ログインが成功した場合、このメソッドが呼び出される。
	 */
	// @RequestMapping("/")
	// public String login(Model model) {

	// 	//メインページ。
	// 	return "index";
	// }

}
