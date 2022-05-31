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

import com.demo.DTO.UserDTO;
import com.demo.DTO.WalletDTO;
import com.demo.models.User;
import com.demo.models.Wallet;
import com.demo.service.UserService;
import com.demo.service.WalletService;

@Controller
@RequestMapping("/wallets")
public class ClientWalletController {

	@Autowired
	private WalletService walletService;

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String listWallet(Model model, Principal principal) {

		User user = userService.GetUserByEmail(principal.getName());

		List<Wallet> wallets = walletService.getByUser(user);

		List<WalletDTO> walletDTOs = wallets.stream().map(e -> e.toDTo()).collect(Collectors.toList());

		model.addAttribute("walletDTOs", walletDTOs);

		return "user/wallet/wallets";

	}

	@GetMapping("/add-wallet")
	public String addNewWalletGet(Model model) {

		WalletDTO walletDTO = new WalletDTO();
		model.addAttribute("walletDTO", walletDTO);

		return "user/wallet/add-wallet";

	}

	@PostMapping("/add-wallet")
	public String addNewWalletPost(Model model, Principal principal,
			@ModelAttribute(name = "walletDTO") WalletDTO walletDTO) {

		User user = userService.GetUserByEmail(principal.getName());

		Wallet wallet = walletService.getByUser(user).stream().filter(e -> e.getName().equals(walletDTO.getName()))
				.findAny().orElse(null);

		if (wallet != null) {
			model.addAttribute("error", "This is wallet name is exist");
			return "user/wallet/add-wallet";
		}

		walletDTO.setUserDTO(user.toDTO());

		walletService.createWallet(walletDTO.toModel());

		return "redirect:/wallets";

	}

	@GetMapping("/edit-wallet/{id}")
	public String editWalletGet(Model model, @PathVariable(name = "id") Long id) {

		WalletDTO walletDTO = walletService.getWalletById(id).toDTo();
		model.addAttribute("walletDTO", walletDTO);

		return "user/wallet/edit-wallet";

	}

	@PostMapping("/edit-wallet/{id}")
	public String addNewWalletPost(Model model, Principal principal, @PathVariable(name = "id") Long id,
			@ModelAttribute(name = "walletDTO") WalletDTO walletDTO) {

		User user = userService.GetUserByEmail(principal.getName());

		Wallet wallet = walletService.getByUser(user).stream().filter(e -> e.getName().equals(walletDTO.getName()))
				.findAny().orElse(null);

		if (wallet != null) {
			model.addAttribute("error", "This is wallet name is exist");
			return "user/wallet/edit-wallet";
		}

		walletDTO.setUserDTO(user.toDTO());

		walletService.editWallet(walletDTO.toModel(), id);

		return "redirect:/wallets";

	}

	@GetMapping("/delete-wallet/{id}")
	public String deleteWallet(Model model, @PathVariable(name = "id") Long id) {

		Wallet wallet = walletService.getWalletById(id);

		walletService.deleteWallet(wallet);

		return "redirect:/wallets";

	}

}
