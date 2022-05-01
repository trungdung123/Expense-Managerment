package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.WalletDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private Double balance;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Wallet(String name, Double balance, User user) {
		super();
		this.name = name;
		this.balance = balance;
		this.user = user;
	}
	
	public WalletDTO toDTo() {
		return new WalletDTO(id, name, balance, user.toDTO());
	}
	
}
