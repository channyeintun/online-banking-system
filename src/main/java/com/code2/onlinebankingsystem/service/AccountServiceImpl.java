package com.code2.onlinebankingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void saveAccount(Account account) {
		accountRepository.save(account);
	}

	@Override
	public List<Account> getAccountsByAccountNumber(String accountNumber) {
		List<Account> accounts = accountRepository.getAccountsByAccountNumber(accountNumber);
		return accounts;
	}

	@Override
	public Account getAccountById(Long id) {
		return accountRepository.getOne(id);
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		return accountRepository.getAccountByAccountNumber(accountNumber);
	}

}
