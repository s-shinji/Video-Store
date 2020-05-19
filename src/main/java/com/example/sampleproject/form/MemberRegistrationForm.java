package com.example.sampleproject.form;

/**
 * 会員登録フォームに入力された値を格納するためのクラス。
 */
public class MemberRegistrationForm {
	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userId) {
		this.userName = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}