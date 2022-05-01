package com.demo.service.impl;

import java.util.List;

import com.demo.models.CategoryType;

public interface CategoryTypeServiceImpl {

	List<CategoryType> getAllCategoryType();

	CategoryType getCategoryTypeById(Long id);

}
