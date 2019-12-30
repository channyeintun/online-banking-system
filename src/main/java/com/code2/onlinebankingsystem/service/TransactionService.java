package com.code2.onlinebankingsystem.service;

import java.time.Instant;
import java.util.List;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.entity.Transaction;

public interface TransactionService {
	public void saveTransaction(Transaction transaction);
	public List<Transaction> getTransactionsFilterByDate(Account account, Instant start, Instant end);
}
