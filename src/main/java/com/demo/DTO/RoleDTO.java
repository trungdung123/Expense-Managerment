package com.demo.DTO;

import com.demo.models.ERole;
import com.demo.models.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {

	private Long id;

	private String name;

	public RoleDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Role toModel () {
		return new Role (ERole.valueOf(name));
	}

}
