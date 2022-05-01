package com.demo.DTO;

import com.demo.models.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

private Long id;
	
	private String name;

	private CategoryTypeDTO typeDTO;

	public CategoryDTO(Long id, String name, CategoryTypeDTO typeDTO) {
		super();
		this.id = id;
		this.name = name;
		this.typeDTO = typeDTO;
	}
	
	public Category toModel () {
		return new Category(name, typeDTO.toModel());
	}
	
}
