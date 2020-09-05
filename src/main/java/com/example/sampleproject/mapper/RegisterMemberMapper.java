package com.example.sampleproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.example.sampleproject.entity.MemberRegistrationEntity;

@Mapper
public interface RegisterMemberMapper {

	//会員情報をUSERテーブルにinsertする。
		@Insert("INSERT INTO users(name, email, password) VALUES(#{name},#{email},#{password})")
		public void insertMemberInfo(MemberRegistrationEntity entity);

	public MemberRegistrationEntity findByEmail(String email);

	public MemberRegistrationEntity findByName(String name);

}