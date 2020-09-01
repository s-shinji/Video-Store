package com.example.sampleproject.controller;

// import com.example.sampleproject.entity.DbUserDetails;

// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
//springとreactでurlがかぶらないようにするため追加
// @RequestMapping("/KdiJ362")
public class LoginController {

	/**
	 * ログイン画面 に遷移する。
	 */
	// @RequestMapping("/login")
	// public String showLoginForm(Model model) {

	// 	//ログイン画面。
	// 	return "login";
	// }

	// @RequestMapping("/login-error")
	// public String loginError(Model model) {
	// 	 model.addAttribute("iserror",true);
	// 	 return "login";
	// }

	@GetMapping("/logouted")
	public int logout() {
		return 0;
	}
}
