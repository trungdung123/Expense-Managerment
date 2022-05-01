package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.CategoryTypeDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category_types")
public class CategoryType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ECategory name;

	public CategoryType(ECategory name) {
		super();
		this.name = name;
	}
	
	public CategoryTypeDTO toDTO () {
		return new CategoryTypeDTO(id, name.toString());
	}
}
