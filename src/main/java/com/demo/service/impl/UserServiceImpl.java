package com.demo.service.impl;

import java.util.List;

import com.demo.models.User;

public interface UserServiceImpl {

	List<User> getAllUser ();
	
	User GetUserById(Long id);
	
	User createUser(User user);
	
	User editUser (User user, Long id);
	
	void deleteUser (User user);
	
}
