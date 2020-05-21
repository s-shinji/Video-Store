package com.example.sampleproject.form;//変更！！

/**
 * 会員登録フォームに入力された値を格納するためのクラス。
 */
public class MemberRegistrationForm {
	private String name;

	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}