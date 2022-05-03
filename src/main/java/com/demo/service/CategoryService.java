package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Category;
import com.demo.models.CategoryType;
import com.demo.models.ECategory;
import com.demo.repository.CategoryRepository;
import com.demo.repository.CategoryTypeRepository;
import com.demo.service.impl.CategoryServiceImpl;

@Service
public class CategoryService implements CategoryServiceImpl{

	@Autowired
	private CategoryRepository CategoryRepository;
	
	@Autowired
	private CategoryTypeRepository categoryTypeRepository;

	@Override
	public List<Category> getAllCategory() {
		return CategoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		return CategoryRepository.findById(id).get();
	}

	@Override
	public Category createCategory(Category Category) {
		
		CategoryType categoryType = categoryTypeRepository.findByName(Category.getType().getName());
		
		Category.setType(categoryType);
		
		return CategoryRepository.save(Category);
	}

	@Override
	public Category editCategory(Category Category, Long id) {
		
		Category CategoryInDB = CategoryRepository.findById(id).get();
		
		CategoryType type = categoryTypeRepository.findByName(Category.getType().getName());
		
		CategoryInDB.setType(type);
		CategoryInDB.setName(Category.getName());
		
		return CategoryRepository.save(CategoryInDB);
		
	}

	@Override
	public void deleteCategory(Category Category) {
		CategoryRepository.delete(Category);
	}
	
}
