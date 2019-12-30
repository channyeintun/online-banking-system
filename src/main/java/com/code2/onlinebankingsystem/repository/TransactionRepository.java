package com.code2.onlinebankingsystem.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code2.onlinebankingsystem.entity.Account;
import com.code2.onlinebankingsystem.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    @Query("Select t from Transaction t where t.account=:account and t.transactionTime between :start and :end")
	public List<Transaction> getTransactionsFilterByDate(@Param("account")Account account,@Param("start")Instant start,@Param("end")Instant end);
}
