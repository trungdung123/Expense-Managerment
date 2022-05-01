package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long>{

}
