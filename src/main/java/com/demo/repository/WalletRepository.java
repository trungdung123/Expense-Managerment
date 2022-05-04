package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

	Wallet findByName(String name);
	
}
