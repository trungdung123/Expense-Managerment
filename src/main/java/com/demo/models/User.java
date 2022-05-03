package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	private String password;
	private String full_name;
	private String email;
	private String phone;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	public User(String username, String password, String full_name, String email, String phone, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.full_name = full_name;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}
	
	public UserDTO toDTO (){
		return new UserDTO(id, username, password, email, full_name, phone, role.toDTO());
	}



}
