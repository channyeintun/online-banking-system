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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.entity.Transaction;
import com.code2.onlinebankingsystem.service.AccountService;
import com.code2.onlinebankingsystem.service.TransactionService;

@Controller
@RequestMapping("/admin/withdrawal")
public class WithDrawalController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	@GetMapping
	public String showWithdrawal(@RequestParam("accountId")Long id,Model model) {
		
		Account account = accountService.getAccountById(id);
		model.addAttribute("account",account);
		
		return "admin/withdrawal";
	}
	
	@PostMapping
	public String processWithdrawal(@RequestParam("accountId")Long id,@RequestParam("amount")BigDecimal amount,RedirectAttributes redirectAttributes) {
		
		Account account = accountService.getAccountById(id);
		
		if(account.getBalance().compareTo(amount)>=0) {
			account.setBalance(account.getBalance().subtract(amount));
			Transaction transaction=new Transaction(account,"Withdrawal","OUT",Instant.now(),amount);
			transactionService.saveTransaction(transaction);
			accountService.saveAccount(account);
		}else {
			redirectAttributes.addAttribute("accountId", account.getId());
			redirectAttributes.addFlashAttribute("error", "This account does not have enought balance.");
			return "redirect:/admin/withdrawal";
		}
		
		return "redirect:/admin/accounts";
	}
	
}
