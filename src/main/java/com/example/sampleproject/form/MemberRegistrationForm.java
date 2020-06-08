package com.example.sampleproject.form;

import javax.validation.constraints.Pattern;
import com.example.sampleproject.validation.FindEmail;

/**
 * 会員登録フォームに入力された値を格納するためのクラス。
 */
public class MemberRegistrationForm {
	private String name;
	@FindEmail
	@Pattern(regexp = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$", message = "Emailが不正です。")
	private String email;

	// @Pattern(regexp =
	// "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])$|^(?=.*[!-/:-@\\[-`{-~])[!-~]*")
	private String password;

	private String passwordConfirmation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}


}