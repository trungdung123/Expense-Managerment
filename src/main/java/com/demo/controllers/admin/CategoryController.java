package com.demo.controllers.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.CategoryDTO;
import com.demo.models.Category;
import com.demo.models.CategoryType;
import com.demo.models.ECategory;
import com.demo.service.CategoryService;
import com.demo.service.CategoryTypeService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryTypeService categoryTypeService;
	
	@GetMapping("")
	public String listCategory (Model model) {
		List<Category> categories = categoryService.getAllCategory();
		List<CategoryDTO> categoryDTOs = categories.stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("categoryDTOs", categoryDTOs);
		
		return "admin/category/categories";
	}
	
	@GetMapping("/add-category")
	public String createNewCategoryGet (Model model) {
		
		CategoryDTO categoryDTO = new CategoryDTO();
		
		model.addAttribute("categoryDTO", categoryDTO);
		
		return "admin/category/add-category";
	}
	
	@PostMapping("/add-category")
	public String createNewCategoryPost (Model model, 
			@ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO) {
		
		CategoryType categoryType = categoryTypeService.getCategoryTypeByName(ECategory.valueOf(categoryDTO.getTypeDTO().getName()));
		
		categoryDTO.setTypeDTO(categoryType.toDTO());
		
		categoryService.createCategory(categoryDTO.toModel());
		
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/edit-category/{id}")
	public String editCategoryGet (Model model, 
			@PathVariable(name = "id") Long id) {
		
		CategoryDTO categoryDTO = categoryService.getCategoryById(id).toDTO();
		
		model.addAttribute("categoryDTO", categoryDTO);
		
		return "admin/category/edit-category";
	}
	
	@PostMapping("/edit-category/{id}")
	public String editCategoryPost (Model model, 
			@ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO, 
			@PathVariable(name = "id") Long id) {
		
		CategoryType categoryType = categoryTypeService.getCategoryTypeByName(ECategory.valueOf(categoryDTO.getTypeDTO().getName()));
		
		categoryDTO.setTypeDTO(categoryType.toDTO());
		
		categoryService.editCategory(categoryDTO.toModel(), id);
		
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/delete-category/{id}")
	public String deleteCategory (Model model, 
			@PathVariable(name = "id") Long id) {
		
		categoryService.deleteCategory(categoryService.getCategoryById(id));
		
		return "redirect:/admin/categories";
	}
	
}
