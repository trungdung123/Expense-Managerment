package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Role;
import com.demo.models.User;
import com.demo.repository.UserRepository;
import com.demo.service.impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User GetUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User createUser(User user) {
		Role role = roleService.getRoleByName(user.getRole().getName());
		user.setRole(role);
		return userRepository.save(user);
	}

	@Override
	public User editUser(User user, Long id) {
		User userInDB = userRepository.findById(id).get();
		userInDB.setPhone(user.getPhone());
		userInDB.setEmail(user.getEmail());
		userInDB.setFull_name(user.getFull_name());
		userInDB.setPassword(user.getPassword());
		userInDB.setUsername(user.getUsername());
		
		Role role = roleService.getRoleByName(user.getRole().getName());
		
		userInDB.setRole(role);
		
		return userRepository.save(userInDB);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public User GetUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
