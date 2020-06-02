package com.example.sampleproject.mapper;

import com.example.sampleproject.entity.Image;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
	public void insertImage(Image image);
}