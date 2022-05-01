package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
