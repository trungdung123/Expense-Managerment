package com.demo.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.UserDTO;
import com.demo.DTO.WalletDTO;
import com.demo.models.User;
import com.demo.models.Wallet;
import com.demo.service.UserService;
import com.demo.service.WalletService;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/wallets")
public class WalletController {

	@Autowired 
	private WalletService walletService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String listWallet (Model model) {
		
		List<Wallet> wallets = walletService.getAllWallet();
		
		List<WalletDTO> walletDTOs = wallets.stream()
				.map(e -> e.toDTo()).collect(Collectors.toList());
		
		model.addAttribute("walletDTOs", walletDTOs);
		
		return "admin/wallet/wallets";
		
	}
	
	@GetMapping("/add-wallet")
	public String addNewWalletGet (Model model) {
		
		List<User> users = userService.getAllUser();
		List<UserDTO> userDTOs = users.stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		WalletDTO walletDTO = new WalletDTO();
		
		model.addAttribute("userDTOs", userDTOs);
		model.addAttribute("walletDTO", walletDTO);
		
		return "admin/wallet/add-wallet";
		
	}
	
	@PostMapping("/add-wallet")
	public String addNewWalletPost (Model model
			, @ModelAttribute(name = "walletDTO") WalletDTO walletDTO) {
		
		User user = userService.GetUserByUsername(walletDTO.getUserDTO().getUsername());
		
		walletDTO.setUserDTO(user.toDTO());
		
		walletService.createWallet(walletDTO.toModel());
		
		return "redirect:/admin/wallets";
		
	}
	
	@GetMapping("/edit-wallet/{id}")
	public String editWalletGet (Model model, 
			@PathVariable(name = "id") Long id) {
		
		List<User> users = userService.getAllUser();
		List<UserDTO> userDTOs = users.stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		WalletDTO walletDTO = walletService.getWalletById(id).toDTo();
		
		model.addAttribute("userDTOs", userDTOs);
		model.addAttribute("walletDTO", walletDTO);
		
		return "admin/wallet/edit-wallet";
		
	}
	
	@PostMapping("/edit-wallet/{id}")
	public String addNewWalletPost (Model model
			, @PathVariable(name = "id") Long id
			, @ModelAttribute(name = "walletDTO") WalletDTO walletDTO) {
		
		User user = userService.GetUserByUsername(walletDTO.getUserDTO().getUsername());
		
		walletDTO.setUserDTO(user.toDTO());
		
		walletService.editWallet(walletDTO.toModel(), id);
		
		return "redirect:/admin/wallets";
		
	}
	
	@GetMapping("/delete-wallet/{id}")
	public String deleteWallet (Model model
			, @PathVariable(name = "id") Long id) {
		
		Wallet wallet = walletService.getWalletById(id);
		
		walletService.deleteWallet(wallet);
		
		return "redirect:/admin/wallets";
		
	}
	
}
