package com.demo.DTO;

import com.demo.models.ETransaction;
import com.demo.models.TransactionType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionTypeDTO {

	private Long id;

	private String name;
	
	public TransactionTypeDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public TransactionType toModel () {
		return new TransactionType(ETransaction.valueOf(name));
	}



}
