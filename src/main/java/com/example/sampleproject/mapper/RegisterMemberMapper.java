package com.example.sampleproject.mapper;

import org.apache.ibatis.annotations.Mapper;
//変更箇所(new)
// import java.util.List;

import com.example.sampleproject.entity.MemberRegistrationEntity;

@Mapper
public interface RegisterMemberMapper {

	//会員情報をUSERテーブルにinsertする。
	public void insertMemberInfo(MemberRegistrationEntity entity);

	//変更箇所(new)
	// public List<MemberRegistrationEntity> findAll();

}