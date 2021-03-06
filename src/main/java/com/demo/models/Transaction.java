package com.demo.models;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.demo.DTO.TransactionDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double value;
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date time;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Transaction(Double value, String description, Date time, User user, Wallet wallet,
			Category category) {
		super();
		this.value = value;
		this.description = description;
		this.time = time;
		this.user = user;
		this.wallet = wallet;
		this.category = category;
	}
	
	public TransactionDTO toDTO () {
		return new TransactionDTO(id, value, description, time, user.toDTO(), wallet.toDTo(), category.toDTO());
	}

}