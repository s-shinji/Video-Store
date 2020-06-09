package com.example.sampleproject.form;

import org.springframework.web.multipart.MultipartFile;

public class ProfileForm {
	private MultipartFile avatar;

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}
}