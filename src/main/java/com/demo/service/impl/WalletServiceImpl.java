package com.demo.service.impl;

import java.util.List;

import com.demo.models.User;
import com.demo.models.Wallet;

public interface WalletServiceImpl {

	List<Wallet> getAllWallet ();
	
	List<Wallet> getByUser (User user);
	
	Wallet getWalletById(Long id);
	
	Wallet getWalletByName(String name);
	
	Wallet createWallet (Wallet wallet);
	
	Wallet editWallet (Wallet wallet, Long id);
	
	void deleteWallet (Wallet wallet);
}
