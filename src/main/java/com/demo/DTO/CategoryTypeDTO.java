package com.demo.DTO;

import com.demo.models.CategoryType;
import com.demo.models.ECategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryTypeDTO {

	private Long id;
	
	private String name;

	public CategoryTypeDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public CategoryType toModel () {
		return new CategoryType(ECategory.valueOf(name));
	}
	
}
