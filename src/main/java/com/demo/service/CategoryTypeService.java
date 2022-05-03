package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.CategoryType;
import com.demo.models.ECategory;
import com.demo.repository.CategoryTypeRepository;
import com.demo.service.impl.CategoryTypeServiceImpl;

@Service
public class CategoryTypeService implements CategoryTypeServiceImpl {

	@Autowired
	private CategoryTypeRepository CategoryTypeRepository;

	@Override
	public List<CategoryType> getAllCategoryType() {
		return CategoryTypeRepository.findAll();
	}

	@Override
	public CategoryType getCategoryTypeById(Long id) {
		return CategoryTypeRepository.findById(id).get();
	}

	@Override
	public CategoryType getCategoryTypeByName(ECategory name) {
		return CategoryTypeRepository.findByName(name);
	}
	
}
