package com.demo.service.impl;

import java.util.List;

import com.demo.models.Category;

public interface CategoryServiceImpl {

	List<Category> getAllCategory();

	Category getCategoryById(Long id);
	
	Category getCategoryByName(String name);

	Category createCategory(Category Category);

	Category editCategory(Category Category, Long id);

	void deleteCategory(Category Category);

}
