package com.synchrony.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.DuplicateResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.synchrony.challenge.controller.UserController;
import com.synchrony.challenge.model.UserInfo;
import com.synchrony.challenge.repository.UserRepository;
import com.synchrony.challenge.util.PasswordUtil;

@Service
public class UserService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserInfo createUser(UserInfo user) {
		logger.info("inside UserService.createUser");
		List<UserInfo> users = getUsers();
		List<String> userNames = users.stream().map(UserInfo :: getUserName).map(String :: trim) .collect(Collectors.toList());
		String userName = user.getUserName().trim();
		if(userNames.contains(userName)) {
			throw new DuplicateResourceException("userName is duplicate");
		}
		
		String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
		user.setPassowrd(hashedPassword);
		return userRepository.save(user);
	}
	
	public List<UserInfo> getUsers() {
		logger.info("inside UserService.getUsersf");

		return userRepository.findAll();
	}
	
	
	public boolean verifyUser(String userName, String password) {
		logger.info("inside UserService.verifyUser");

		UserInfo user = userRepository.findByUserName(userName);
		if(user == null) {
			return false;
		}
		//return user.getUserName().trim().equals(userName.trim()) && user.getPassword().trim().equals(password.trim());
		return user.getUserName().trim().equals(userName.trim()) && PasswordUtil.isPasswordValid(password, user.getPassword());

	}
	
	public UserInfo getUserByName(String userName) {
		logger.info("inside UserService.getUserByName");

		return userRepository.findByUserName(userName);
	}
}
