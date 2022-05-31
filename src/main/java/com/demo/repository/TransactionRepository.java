package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Transaction;
import com.demo.models.User;
import com.demo.models.Wallet;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByWallet(Wallet wallet);
	
	List<Transaction> findByUser(User user);
	
}
