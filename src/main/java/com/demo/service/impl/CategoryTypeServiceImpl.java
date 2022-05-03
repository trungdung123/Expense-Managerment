package com.demo.service.impl;

import java.util.List;

import com.demo.models.CategoryType;
import com.demo.models.ECategory;

public interface CategoryTypeServiceImpl {

	List<CategoryType> getAllCategoryType();

	CategoryType getCategoryTypeById(Long id);
	
	CategoryType getCategoryTypeByName(ECategory name);

}
