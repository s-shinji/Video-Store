package com.example.sampleproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.sampleproject.entity.Account;

@Mapper
public interface LoginMapper {

	//USERテーブルからユーザ名とパスワードを取得。
	public Account findAccount(String name);
}