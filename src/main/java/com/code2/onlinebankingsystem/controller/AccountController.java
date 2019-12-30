package com.code2.onlinebankingsystem.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.entity.User;
import com.code2.onlinebankingsystem.service.AccountService;
import com.code2.onlinebankingsystem.service.UserService;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public String showAccounts() {
		return "admin/accounts";
	}
	
	@GetMapping("/new")
	public String showCreateForm(@RequestParam("userId")Long userId,Model model) {
		
		User user = userService.getUserById(userId);
		Account account = new Account();
		account.setUser(user);
		
		model.addAttribute("account",account);
		
		return "admin/new-account";
	}
	
	@PostMapping
	public String createAccount(@ModelAttribute("account")Account account) {
		account.setBalance(new BigDecimal(0));
		accountService.saveAccount(account);
		return "redirect:/admin/users";
	}
	
	@PostMapping("/search")
	public String searchAccount(@RequestParam("accountNumber")String accountNumber,Model model) {
		List<Account> accounts = accountService.getAccountsByAccountNumber(accountNumber+"%");
		model.addAttribute("accounts",accounts);
		return "admin/accounts";
	}
	
}
