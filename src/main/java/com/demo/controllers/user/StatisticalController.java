package com.demo.controllers.user;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.TransactionDTO;
import com.demo.models.ECategory;
import com.demo.models.User;
import com.demo.service.TransactionService;
import com.demo.service.UserService;

@Controller
@RequestMapping("")
public class StatisticalController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/statistical")
	public String getStatistical (Model model, Principal principal) {
		
		User user = userService.GetUserByEmail(principal.getName());
		
		List<TransactionDTO> transactionDTOs = transactionService.getTransactionByUser(user)
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());
		
		Double income = (double) 0;
		Double expense = (double) 0;
		
		for (TransactionDTO i : transactionDTOs) {
			if (i.getCategoryDTO().getTypeDTO().getName().equals("CATEGORY_IN")) 
				income += i.getValue();
			else expense += i.getValue();
		}
		
		model.addAttribute("income", income);
		model.addAttribute("expense", expense);
		
		return "/user/statistical/satisticals";
	}
	
}
