package com.demo.DTO;

import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private Long id;
	
	private String username;
	private String password ;
	private String email;
	
	private String full_name;
	private String phone;

	private RoleDTO roleDTO;
	
	public UserDTO(Long id, String username, String password, String email, String full_name, String phone,
			RoleDTO role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.full_name = full_name;
		this.phone = phone;
		this.roleDTO = role;
	}
	
	public User toModel () {
		return new User(username, password, full_name, email, phone, roleDTO.toModel());
	}




}
