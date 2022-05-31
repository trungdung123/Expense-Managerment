package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.User;
import com.demo.models.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

	Wallet findByName(String name);
	
	List<Wallet> findByUser(User user);
}
