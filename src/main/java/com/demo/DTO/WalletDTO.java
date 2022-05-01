package com.demo.DTO;

import com.demo.models.User;
import com.demo.models.Wallet;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletDTO {
	
	private Long id;
	
	private String name;
	private Double balance;
	
	private UserDTO userDTO;

	public WalletDTO(Long id, String name, Double balance, UserDTO userDTO) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.userDTO = userDTO;
	}
	
	public Wallet toModel () {
		return new Wallet(name, balance, userDTO.toModel());
	}
}
