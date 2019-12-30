package com.code2.onlinebankingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code2.onlinebankingsystem.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query("Select a from Account a where a.accountNumber like :accNum")
	public List<Account> getAccountsByAccountNumber(@Param("accNum")String accountNumber);

	@Query("Select b from Account b where b.accountNumber=:accNum")
	public Account getAccountByAccountNumber(@Param("accNum")String accountNumber);

}
