package com.demo.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.models.User;

public interface UserServiceImpl extends UserDetailsService{

	List<User> getAllUser ();
	
	User GetUserById(Long id);
	
	User GetUserByUsername(String username);
	
	User GetUserByEmail(String email);
	
	User createUser(User user);
	
	User editUser (User user, Long id);
	
	void deleteUser (User user);
	
}
