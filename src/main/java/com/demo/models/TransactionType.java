package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.TransactionTypeDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transaction_types")
public class TransactionType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ETransaction name;

	public TransactionType(ETransaction name) {
		super();
		this.name = name;
	}
	
	public TransactionTypeDTO toDTO () {
		return new TransactionTypeDTO(id, name.toString());
	}
	
}
