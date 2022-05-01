package com.demo.service.impl;

import java.util.List;

import com.demo.models.Transaction;

public interface TransactionServiceImpl {

	List<Transaction> getAllTransaction();

	Transaction getTransactionById(Long id);

	Transaction createTransaction(Transaction Transaction);

	Transaction editTransaction(Transaction Transaction, Long id);

	void deleteTransaction(Transaction Transaction);

}
