package com.synchrony.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImgurData {
	
	private String id;
	private String deletehash;
	private String type;
	private Integer width;
	private Integer height;
	private Integer size;
	private Long datetime;
	
	public ImgurData() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeletehash() {
		return deletehash;
	}
	public void setDeletehash(String deletehash) {
		this.deletehash = deletehash;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Long getDatetime() {
		return datetime;
	}
	public void setDatetime(Long datetime) {
		this.datetime = datetime;
	}

}
