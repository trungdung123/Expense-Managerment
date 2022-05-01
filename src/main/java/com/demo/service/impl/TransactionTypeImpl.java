package com.demo.service.impl;

import java.util.List;

import com.demo.models.TransactionType;

public interface TransactionTypeImpl {

	List<TransactionType> getAllTransactionType ();
	
	TransactionType getTransactionTypeById (Long id);
	
}
