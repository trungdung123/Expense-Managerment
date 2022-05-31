package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Category;
import com.demo.models.ECategory;
import com.demo.models.Transaction;
import com.demo.models.User;
import com.demo.models.Wallet;
import com.demo.repository.CategoryRepository;
import com.demo.repository.TransactionRepository;
import com.demo.repository.UserRepository;
import com.demo.repository.WalletRepository;
import com.demo.service.impl.TransactionServiceImpl;

@Service
public class TransactionService implements TransactionServiceImpl {

	@Autowired
	private TransactionRepository TransactionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private CategoryRepository categoryRepository;

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

		User user = userRepository.findByUsername(Transaction.getUser().getUsername());

		Wallet wallet = walletRepository.findByUser(user).stream()
				.filter(e -> e.getName().equals(Transaction.getWallet().getName())).findAny().orElse(null);

		Category category = categoryRepository.findByName(Transaction.getCategory().getName());

		if (category.getType().getName().equals(ECategory.CATEGORY_IN))
			wallet.setBalance(wallet.getBalance() + Transaction.getValue());
		else
			wallet.setBalance(wallet.getBalance() - Transaction.getValue());

		Transaction.setCategory(category);
		Transaction.setUser(user);
		Transaction.setWallet(wallet);

		return TransactionRepository.save(Transaction);
	}

	@Override
	public Transaction editTransaction(Transaction Transaction, Long id) {

		Transaction TransactionInDB = TransactionRepository.findById(id).get();
		Wallet walletInDB = TransactionInDB.getWallet();
		Category categoryInDB = TransactionInDB.getCategory();

		if (categoryInDB.getType().getName().equals(ECategory.CATEGORY_IN))
			walletInDB.setBalance(walletInDB.getBalance() - TransactionInDB.getValue());
		else
			walletInDB.setBalance(walletInDB.getBalance() + TransactionInDB.getValue());

		walletRepository.save(walletInDB);

		User user = userRepository.findByUsername(Transaction.getUser().getUsername());

		Wallet wallet = walletRepository.findByUser(user).stream()
				.filter(e -> e.getName().equals(Transaction.getWallet().getName())).findAny().orElse(null);

		Category category = categoryRepository.findByName(Transaction.getCategory().getName());

		if (category.getType().getName().equals(ECategory.CATEGORY_IN))
			wallet.setBalance(wallet.getBalance() + Transaction.getValue());
		else
			wallet.setBalance(wallet.getBalance() - Transaction.getValue());

		walletRepository.save(wallet);

		TransactionInDB.setCategory(category);
		TransactionInDB.setUser(user);
		TransactionInDB.setWallet(wallet);

		TransactionInDB.setDescription(Transaction.getDescription());
		TransactionInDB.setTime(Transaction.getTime());
		TransactionInDB.setValue(Transaction.getValue());

		return TransactionRepository.save(TransactionInDB);

	}

	@Override
	public void deleteTransaction(Transaction Transaction) {

		Wallet wallet = walletRepository.findByUser(Transaction.getUser()).stream()
				.filter(e -> e.getName().equals(Transaction.getWallet().getName()))
				.findAny()
				.orElse(null);

		Category category = categoryRepository.findByName(Transaction.getCategory().getName());

		if (category.getType().getName().equals(ECategory.CATEGORY_IN))
			wallet.setBalance(wallet.getBalance() - Transaction.getValue());
		else
			wallet.setBalance(wallet.getBalance() + Transaction.getValue());

		walletRepository.save(wallet);

		TransactionRepository.delete(Transaction);
	}

	@Override
	public List<Transaction> getTransactionByUser(User user) {
		return TransactionRepository.findByUser(user);
	}

}
