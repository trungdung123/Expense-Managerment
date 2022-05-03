package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.CategoryType;
import com.demo.models.ECategory;

public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long>{

	CategoryType findByName(ECategory name);
	
}
