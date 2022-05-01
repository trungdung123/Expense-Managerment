package com.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/transactions")
public class TransactionController {

	@GetMapping("")
	public String listTransaction (Model model) {
		return "admin/transaction/transactions";
	}
	
}
