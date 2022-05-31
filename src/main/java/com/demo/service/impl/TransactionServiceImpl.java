package com.demo.service.impl;

import java.util.List;

import com.demo.models.Transaction;
import com.demo.models.User;

public interface TransactionServiceImpl {

	List<Transaction> getAllTransaction();
	
	List<Transaction> getTransactionByUser(User user);

	Transaction getTransactionById(Long id);

	Transaction createTransaction(Transaction Transaction);

	Transaction editTransaction(Transaction Transaction, Long id);

	void deleteTransaction(Transaction Transaction);

}
