package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Transaction;
import com.demo.repository.TransactionRepository;
import com.demo.service.impl.TransactionServiceImpl;

@Service
public class TransactionService implements TransactionServiceImpl{

	@Autowired
	private TransactionRepository TransactionRepository;

	@Override
	public List<Transaction> getAllTransaction() {
		return TransactionRepository.findAll();
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return TransactionRepository.findById(id).get();
	}

	@Override
	public Transaction createTransaction(Transaction Transaction) {
		return TransactionRepository.save(Transaction);
	}

	@Override
	public Transaction editTransaction(Transaction Transaction, Long id) {
		
		Transaction TransactionInDB = TransactionRepository.findById(id).get();
		
		TransactionInDB.setCategory(Transaction.getCategory());
		TransactionInDB.setDescription(Transaction.getDescription());
		TransactionInDB.setTime(Transaction.getTime());
		TransactionInDB.setType(Transaction.getType());
		TransactionInDB.setUser(Transaction.getUser());
		TransactionInDB.setValue(Transaction.getValue());
		TransactionInDB.setWallet(Transaction.getWallet());
		
		return TransactionRepository.save(TransactionInDB);
		
	}

	@Override
	public void deleteTransaction(Transaction Transaction) {
		TransactionRepository.delete(Transaction);
	}
	
}
