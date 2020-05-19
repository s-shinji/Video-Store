package com.example.sampleproject.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class DbUserDetails extends User {
	//DBから取得したユーザ情報。
	private final Account account;

	public DbUserDetails(Account account,
			Collection<GrantedAuthority> authorities) {
		super(account.getName(), account.getPassword(),
				true, true, true, true, authorities);

		this.account = account;
	}

	public Account getAccount() {
		return account;
	}
	
	public String getPassword() {
		return account.getPassword();
	}

	public String getUsername() {
		return account.getName();
	}
	//変更箇所
	public int getUserId() {
		return account.getId();
	}

}