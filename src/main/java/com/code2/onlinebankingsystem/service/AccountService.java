package com.code2.onlinebankingsystem.service;

import java.util.List;

import com.code2.onlinebankingsystem.entity.Account;

public interface AccountService {
	public void saveAccount(Account account);
	public List<Account> getAccountsByAccountNumber(String accountNumber);
	public Account getAccountById(Long id);
	public Account getAccountByAccountNumber(String accountNumber);
}
