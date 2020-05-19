package com.example.sampleproject.entity;

// import java.util.List;

import javax.persistence.Column;
// import javax.persistence.OneToMany;

/**
 * ログインに必要な会員情報を格納するクラス。
 */
public class Account {
	// 変更箇所
	private int id;
	// 今回の場合は直接DBに制約をかけてテーブルを作成したため、特に必要ない？
	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String password;

	// @OneToMany
	// @Column(nullable = true)
	// private List<Movie> movies;

	public String getName() {
		return name;
	}
	//変更箇所
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	//ここまで

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