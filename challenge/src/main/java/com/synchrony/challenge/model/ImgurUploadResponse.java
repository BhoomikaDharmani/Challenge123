package com.synchrony.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ImgurUploadResponse {
	
	private Integer status;
	private boolean success;
	private ImgurData data;
	
	public ImgurUploadResponse() {
		
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ImgurData getData() {
		return data;
	}
	public void setData(ImgurData data) {
		this.data = data;
	}

}
