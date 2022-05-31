package com.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.models.Transaction;
import com.demo.models.User;
import com.demo.models.Wallet;
import com.demo.repository.TransactionRepository;
import com.demo.repository.UserRepository;
import com.demo.repository.WalletRepository;
import com.demo.service.impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User GetUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User createUser(User user) {
		Role role = roleService.getRoleByName(user.getRole().getName());
		user.setRole(role);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

	@Override
	public User editUser(User user, Long id) {
		User userInDB = userRepository.findById(id).get();
		userInDB.setPhone(user.getPhone());
		userInDB.setEmail(user.getEmail());
		userInDB.setFull_name(user.getFull_name());
		userInDB.setPassword(user.getPassword());
		userInDB.setUsername(user.getUsername());
		
		Role role = roleService.getRoleByName(user.getRole().getName());
		
		userInDB.setRole(role);
		
		return userRepository.save(userInDB);
	}

	@Override
	public void deleteUser(User user) {
		List<Wallet> wallets = walletRepository.findByUser(user);
		for (Wallet i : wallets) {
			List<Transaction> transactions = transactionRepository.findByWallet(i);
			for (Transaction j : transactions)
				transactionRepository.delete(j);
			walletRepository.delete(i);
		}
		userRepository.delete(user);
	}

	@Override
	public User GetUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		
		if (user == null)
			throw new UsernameNotFoundException("Invalid Username or Password");
		
		return new org.springframework.security.core.userdetails.User(
					user.getEmail(), user.getPassword(), 
					mapRolesToAuthorities(new HashSet<Role>(Arrays.asList(user.getRole())))
				);
		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> 
			new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
		
	}

	@Override
	public User GetUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
