package com.code2.onlinebankingsystem.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.entity.Transaction;
import com.code2.onlinebankingsystem.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	@Override
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}
	@Override
	public List<Transaction> getTransactionsFilterByDate(Account account, Instant start, Instant end) {
		return transactionRepository.getTransactionsFilterByDate(account,start,end);
	}

}
