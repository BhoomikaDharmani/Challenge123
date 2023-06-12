package com.synchrony.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.DuplicateResourceException;
import org.springframework.stereotype.Service;

import com.synchrony.challenge.model.UserInfo;
import com.synchrony.challenge.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserInfo createUser(UserInfo user) {
		
		List<UserInfo> users = getUsers();
		List<String> userNames = users.stream().map(UserInfo :: getUserName).map(String :: trim) .collect(Collectors.toList());
		String userName = user.getUserName().trim();
		if(userNames.contains(userName)) {
			throw new DuplicateResourceException("userName is duplicate");
		}
		return userRepository.save(user);
	}
	
	public List<UserInfo> getUsers() {
		return userRepository.findAll();
	}
	
	
	public boolean verifyUser(String userName, String password) {
		UserInfo user = userRepository.findByUserName(userName);
		if(user == null) {
			return false;
		}
		return user.getUserName().trim().equals(userName.trim()) && user.getPassword().trim().equals(password.trim());
	}
	
	public UserInfo getUserByName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
