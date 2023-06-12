package com.synchrony.challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_MAPPING_SEQ")
	@SequenceGenerator(name = "IMAGE_MAPPING_SEQ",sequenceName = "IMAGE_MAPPING_SEQ", allocationSize = 1) 
	private Integer id;
	
	@Column(name = "USERID")
	private Integer userId;
	
	@Column(name = "IMAGEID")
	private String imageId;
	
	@Column(name = "IMAGEDELETEHASH")
	private String imageDeleteHash;
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	public void setImageDeleteHash(String imageDeleteHash) {
		this.imageDeleteHash = imageDeleteHash;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public String getImageId() {
		return this.imageId;
	}
	
	public String getImageDeleteHash() {
		return this.imageDeleteHash;
	}
}
