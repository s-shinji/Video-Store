package com.example.sampleproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.sampleproject.entity.MemberRegistrationEntity;

@Mapper
public interface RegisterMemberMapper {

	//会員情報をUSERテーブルにinsertする。
	public void insertMemberInfo(MemberRegistrationEntity entity);
}