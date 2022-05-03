package com.demo.controllers.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.demo.models.Transaction;
import com.demo.models.User;
import com.demo.models.Wallet;
import com.demo.service.CategoryService;
import com.demo.service.TransactionService;
import com.demo.service.TransactionTypeService;
import com.demo.service.UserService;
import com.demo.service.WalletService;

@Controller
@RequestMapping("/admin/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private TransactionTypeService transactionTypeService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String listTransaction (Model model) {
		
		List<Transaction> transactions = transactionService.getAllTransaction();
		List<TransactionDTO> transactionDTOs = transactions.stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("transactionDTOs", transactionDTOs);
		
		return "admin/transaction/transactions";
	}
	
	@GetMapping("/add-transaction")
	public String createNewTransactionGet (Model model) {
		
		TransactionDTO transactionDTO = new TransactionDTO();
		
		List<UserDTO> userDTOs = userService.getAllUser().stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		List<WalletDTO> walletDTOs = walletService.getAllWallet().stream()
				.map(e -> e.toDTo()).collect(Collectors.toList());
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory().stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("transactionDTO", transactionDTO);
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("userDTOs", userDTOs);
		model.addAttribute("walletDTOs", walletDTOs);
		
		return "admin/transaction/add-transaction";
	}
	
	@PostMapping("/add-transaction")
	public String createNewTransactionPost (Model model, 
			@ModelAttribute(name = "transactionDTO") TransactionDTO transactionDTO) {
		
		User user = userService.GetUserByUsername(transactionDTO.getUserDTO().getUsername());

		
		return "redirect:/admin/transactions";
	}
	
}
