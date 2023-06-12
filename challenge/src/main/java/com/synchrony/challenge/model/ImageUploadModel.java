package com.synchrony.challenge.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadModel {
	
	private String userName;
	private String password;
	private MultipartFile image;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
