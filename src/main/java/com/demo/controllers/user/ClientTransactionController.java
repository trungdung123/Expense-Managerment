package com.demo.controllers.user;

import java.security.Principal;
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
import com.demo.DTO.TransactionDTO;
import com.demo.DTO.UserDTO;
import com.demo.DTO.WalletDTO;
import com.demo.models.Category;
import com.demo.models.User;
import com.demo.models.Wallet;
import com.demo.service.CategoryService;
import com.demo.service.TransactionService;
import com.demo.service.UserService;
import com.demo.service.WalletService;

@Controller
@RequestMapping("/transactions")
public class ClientTransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserService userService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String home() {
		return "redirect:/";
	}

	@GetMapping("/add-transaction")
	public String addNewTransaction(Model model, Principal principal) {

		User user = userService.GetUserByEmail(principal.getName());

		TransactionDTO transactionDTO = new TransactionDTO();

		List<WalletDTO> walletDTOs = walletService.getByUser(user).stream().map(e -> e.toDTo())
				.collect(Collectors.toList());

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory().stream().map(e -> e.toDTO())
				.collect(Collectors.toList());

		model.addAttribute("transactionDTO", transactionDTO);
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("walletDTOs", walletDTOs);
		model.addAttribute("userDTO", user.toDTO());

		return "/user/transaction/add-transaction";
	}

	@PostMapping("/add-transaction")
	public String addNewTransactionPost(Model model, Principal principal,
			@ModelAttribute(name = "transactionDTO") TransactionDTO transactionDTO) {

		User user = userService.GetUserByEmail(principal.getName());

		Wallet wallet = walletService.getByUser(user).stream()
				.filter(e -> e.getName().equals(transactionDTO.getWalletDTO().getName())).findAny().orElse(null);

		Category category = categoryService.getCategoryByName(transactionDTO.getCategoryDTO().getName());

		transactionDTO.setUserDTO(user.toDTO());
		transactionDTO.setWalletDTO(wallet.toDTo());
		transactionDTO.setCategoryDTO(category.toDTO());

		transactionService.createTransaction(transactionDTO.toModel());

		return "redirect:/transactions";
	}

	@GetMapping("/edit-transaction/{id}")
	public String editTransactionGet(Model model, Principal principal, @PathVariable(name = "id") Long id) {

		User user = userService.GetUserByEmail(principal.getName());
		TransactionDTO transactionDTO = transactionService.getTransactionById(id).toDTO();

		List<WalletDTO> walletDTOs = walletService.getByUser(user).stream().map(e -> e.toDTo())
				.collect(Collectors.toList());

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory().stream().map(e -> e.toDTO())
				.collect(Collectors.toList());

		model.addAttribute("transactionDTO", transactionDTO);
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("walletDTOs", walletDTOs);

		return "user/transaction/edit-transaction";
	}

	@PostMapping("/edit-transaction/{id}")
	public String editTransactionPost(Model model, Principal principal,
			@ModelAttribute(name = "transactionDTO") TransactionDTO transactionDTO,
			@PathVariable(name = "id") Long id) {

		User user = userService.GetUserByEmail(principal.getName());

		Wallet wallet = walletService.getByUser(user).stream()
				.filter(e -> e.getName().equals(transactionDTO.getWalletDTO().getName())).findAny().orElse(null);

		Category category = categoryService.getCategoryByName(transactionDTO.getCategoryDTO().getName());

		transactionDTO.setUserDTO(user.toDTO());
		transactionDTO.setWalletDTO(wallet.toDTo());
		transactionDTO.setCategoryDTO(category.toDTO());

		transactionService.editTransaction(transactionDTO.toModel(), id);

		return "redirect:/transactions";
	}

	@GetMapping("/delete-transaction/{id}")
	public String deleteTransaction(Model model, @PathVariable(name = "id") Long id) {

		transactionService.deleteTransaction(transactionService.getTransactionById(id));

		return "redirect:/transactions";
	}

}
