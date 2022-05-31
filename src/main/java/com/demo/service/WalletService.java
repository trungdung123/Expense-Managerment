package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.User;
import com.demo.models.Wallet;
import com.demo.repository.WalletRepository;
import com.demo.service.impl.WalletServiceImpl;

@Service
public class WalletService implements WalletServiceImpl{
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Wallet> getAllWallet() {
		return walletRepository.findAll();
	}

	@Override
	public Wallet getWalletById(Long id) {
		return walletRepository.findById(id).get();
	}

	@Override
	public Wallet createWallet(Wallet wallet) {
		
		User user = userService.GetUserByUsername(wallet.getUser().getUsername());
		
		wallet.setUser(user);
		
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet editWallet(Wallet wallet, Long id) {
		
		Wallet walletInDB = walletRepository.findById(id).get();
		
		walletInDB.setBalance(wallet.getBalance());
		walletInDB.setName(wallet.getName());
		
		User user = userService.GetUserByUsername(wallet.getUser().getUsername());
		
		walletInDB.setUser(user);
		
		return walletRepository.save(walletInDB);
		
	}

	@Override
	public void deleteWallet(Wallet wallet) {
		walletRepository.delete(wallet);
	}

	@Override
	public Wallet getWalletByName(String name) {
		return walletRepository.findByName(name);
	}

	@Override
	public List<Wallet> getByUser(User user) {
		return walletRepository.findByUser(user);
	}

}
