package com.demo.DTO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.demo.models.Transaction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionDTO {

private Long id;
	
	private Double value;
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date time;

	private UserDTO userDTO;
	private WalletDTO walletDTO;
	private CategoryDTO categoryDTO;
	
	public TransactionDTO(Long id, Double value, String description, Date time,
			UserDTO userDTO, WalletDTO walletDTO, CategoryDTO categoryDTO) {
		super();
		this.id = id;
		this.value = value;
		this.description = description;
		this.time = time;
		this.userDTO = userDTO;
		this.walletDTO = walletDTO;
		this.categoryDTO = categoryDTO;
	}
	
	public Transaction toModel () {
		return new Transaction(value, description, time, userDTO.toModel(), walletDTO.toModel(), categoryDTO.toModel());
	}
	
}
