package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.TransactionType;
import com.demo.repository.TransactionTypeRepository;
import com.demo.service.impl.TransactionTypeImpl;

@Service
public class TransactionTypeService implements TransactionTypeImpl{

	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	@Override
	public List<TransactionType> getAllTransactionType() {
		return transactionTypeRepository.findAll();
	}

	@Override
	public TransactionType getTransactionTypeById(Long id) {
		return transactionTypeRepository.findById(id).get();
	}

}
