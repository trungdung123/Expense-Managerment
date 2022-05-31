package com.demo.controllers.user;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.CategoryDTO;
import com.demo.DTO.TransactionDTO;
import com.demo.DTO.UserDTO;
import com.demo.DTO.WalletDTO;
import com.demo.models.Category;
import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.models.User;
import com.demo.service.CategoryService;
import com.demo.service.TransactionService;
import com.demo.service.UserService;
import com.demo.service.WalletService;

@Controller
@RequestMapping("")
public class UserHomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public String Home (Model model, Principal principal) {
		
		User user = (User) userService.GetUserByEmail(principal.getName());
		
		List<TransactionDTO> transactionDTOs = transactionService.getTransactionByUser(user)
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());
		
		List<WalletDTO> walletDTOs = walletService.getByUser(user).stream()
				.map(e -> e.toDTo()).collect(Collectors.toList());
		
		model.addAttribute("walletDTOs", walletDTOs);
		model.addAttribute("transactionDTOs", transactionDTOs);
		model.addAttribute("userDTO", user.toDTO());
		
		return "/user/home";
	}
	
	@GetMapping("/login")
	public String loginGet (Model model) {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(Model model,
			@ModelAttribute(name = "userDTO") UserDTO userDTO) {
		
		User user = userService.GetUserByEmail(userDTO.getEmail());
		User user1 = userService.GetUserByUsername(userDTO.getUsername());
		
		if (user != null || user1 != null) {
			model.addAttribute("error", "User or Email is exist in system");
			return "/register";
		}
		
		System.out.println("heheheheh");
		userDTO.setRoleDTO((new Role(ERole.ROLE_USER)).toDTO());
		userService.createUser(userDTO.toModel());
		return "redirect:/login";
	}
	
	@GetMapping("/categories")
	public String listCategory (Model model) {
		List<Category> categories = categoryService.getAllCategory();
		List<CategoryDTO> categoryDTOs = categories.stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("categoryDTOs", categoryDTOs);
		
		return "user/category/categories";
	}
	
}
