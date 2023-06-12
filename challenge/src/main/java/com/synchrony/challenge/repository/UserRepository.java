package com.synchrony.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synchrony.challenge.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo findByUserName(String userName);
}
