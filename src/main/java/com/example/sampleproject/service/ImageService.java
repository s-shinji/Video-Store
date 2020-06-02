package com.example.sampleproject.service;

import javax.transaction.Transactional;

import com.example.sampleproject.entity.Image;
import com.example.sampleproject.mapper.ImageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImageService {

	@Autowired
	ImageMapper imageMapper;
	
	public void save(Image image) {
		imageMapper.insertImage(image);
	}
}