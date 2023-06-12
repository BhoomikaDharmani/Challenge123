package com.synchrony.challenge.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_INFO_SEQ")
	@SequenceGenerator(name = "USER_INFO_SEQ",sequenceName = "USER_INFO_SEQ", allocationSize = 1) 
    private Integer id;
	
	@Column(name = "USERNAME")
    private String userName;
	@Column(name = "PASSWORD")
    private String password;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassowrd(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassword() {
		return this.password;
	}

}
