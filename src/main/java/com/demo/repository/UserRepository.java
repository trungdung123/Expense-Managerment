package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername (String username);
	
}
