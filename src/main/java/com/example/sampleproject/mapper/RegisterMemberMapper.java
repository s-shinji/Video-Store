package com.example.sampleproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.sampleproject.entity.MemberRegistrationEntity;

@Mapper
public interface RegisterMemberMapper {

	//会員情報をUSERテーブルにinsertする。
	public void insertMemberInfo(MemberRegistrationEntity entity);
	// @Insert("INSERT INTO users(name, email, password) VALUES(#{name},#{email},#{password})")
	// public void insertMemberInfo(MemberRegistrationEntity entity);

	public MemberRegistrationEntity findByEmail(String email);
	// @Select("SELECT email FROM users WHERE email = #{email}")
	// public MemberRegistrationEntity findByEmail(String email);


	public MemberRegistrationEntity findByName(String name);
	// @Select("SELECT name FROM users WHERE name = #{name}")
	// public MemberRegistrationEntity findByName(String name);


}