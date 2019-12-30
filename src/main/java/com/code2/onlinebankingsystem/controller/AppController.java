package com.code2.onlinebankingsystem.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.entity.Transaction;
import com.code2.onlinebankingsystem.entity.User;
import com.code2.onlinebankingsystem.service.AccountService;
import com.code2.onlinebankingsystem.service.TransactionService;
import com.code2.onlinebankingsystem.service.UserService;

@Controller
public class AppController {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	@Autowired
	TransactionService transactionService;

	@GetMapping("/")
	public String showHomePage(Principal principal, Model model) {
		User currentUser = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", currentUser);
		return "home";
	}

	@GetMapping("/transfer")
	public String showTransferPage(Principal principal, Model model, HttpServletRequest request) {
		String accountNumber = request.getParameter("accountNumber");
		if (accountNumber != null) {
			Account account = accountService.getAccountByAccountNumber(accountNumber);
			model.addAttribute("account", account);
		}
		User currentUser = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", currentUser);
		return "transfer";
	}

	@PostMapping("/transfer")
	public String processTransfer(Principal principal, Model model, @RequestParam("accountNumber") String accountNumber,
			@RequestParam("amount") BigDecimal amount) {
		Account ToAccount = accountService.getAccountByAccountNumber(accountNumber);
		User currentUser = userService.getUserByUsername(principal.getName());
		Account FromAccount = currentUser.getAccount();

		if (FromAccount.getBalance().compareTo(amount) >= 0) {
			Transaction transaction1 = new Transaction(FromAccount, "From Account", "OUT", Instant.now(), amount);
			Transaction transaction2 = new Transaction(ToAccount, "To Account", "IN", Instant.now(), amount);
			transactionService.saveTransaction(transaction1);
			transactionService.saveTransaction(transaction2);

			FromAccount.setBalance(FromAccount.getBalance().subtract(amount));
			ToAccount.setBalance(ToAccount.getBalance().add(amount));

			accountService.saveAccount(FromAccount);
			accountService.saveAccount(ToAccount);
			return "success-transfer";
		}
		return "error-transfer";
	}
	@GetMapping("/transactions")
	public String showTransactions(Principal principal,Model model) {
		User currentUser = userService.getUserByUsername(principal.getName());
		Account account = currentUser.getAccount();
		List<Transaction> transactions=account.getTransactions();
		model.addAttribute("transactions", transactions);
		return "transactions";
	}
	@GetMapping("/transactions/filter")
	public String showTransactionsByDate(Principal principal,@RequestParam("startDate")String startDateStr,@RequestParam("endDate")String endDateStr,Model model) throws ParseException {
		User currentUser = userService.getUserByUsername(principal.getName());
		
		Account account = currentUser.getAccount();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = dateFormat.parse(startDateStr);
		Date endDate = dateFormat.parse(endDateStr);
		
		Instant start = startDate.toInstant();
		Instant end = endDate.toInstant().plusSeconds(86400);
		
		DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a").withZone(ZoneId.of("Asia/Rangoon"));
		model.addAttribute("dFormat",dFormat);

		
		List<Transaction> transactions = transactionService.getTransactionsFilterByDate(account,start,end);
		
		model.addAttribute("transactions",transactions);
		
		return "transactions";
	}
}
