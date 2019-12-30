package com.code2.onlinebankingsystem.controller;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.entity.Transaction;
import com.code2.onlinebankingsystem.service.AccountService;
import com.code2.onlinebankingsystem.service.TransactionService;

@Controller
@RequestMapping("/admin/deposit")
public class DepositController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public String showDeposit(@RequestParam("accountId") Long id, Model model) {
		Account account = accountService.getAccountById(id);
		model.addAttribute(account);
		return "admin/deposit";
	}

	@PostMapping
	public String processDeposit(@RequestParam("accountId") Long id, @RequestParam("amount") BigDecimal amount) {

		Account account = accountService.getAccountById(id);
		account.setBalance(account.getBalance().add(amount));
		Transaction transaction = new Transaction(account, "Deposit", "IN", Instant.now(), amount);
		transactionService.saveTransaction(transaction);
		accountService.saveAccount(account);

		return "redirect:/admin/accounts";
	}

}
