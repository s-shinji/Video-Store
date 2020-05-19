package com.example.sampleproject.entity;

/**
 * DBに入れる値を格納するクラス。
 */
//変更箇所
public class MemberRegistrationEntity {
	// 変更箇所
	// private int id;

	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}
	// 変更箇所
	// public int getId() {
	// 	return id;
	// }
	// public void setId(int id) {
	// 	this.id = id;
	// }

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}