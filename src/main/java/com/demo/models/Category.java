package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.CategoryDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private CategoryType type;

	public Category(String name, CategoryType type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public CategoryDTO toDTO () {
		return new CategoryDTO(id, name, type.toDTO());
	}
	
}
