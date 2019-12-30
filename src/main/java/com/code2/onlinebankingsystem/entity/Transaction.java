package com.code2.onlinebankingsystem.entity;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	private String type;

	@Column(name = "transaction_time")
	private Instant transactionTime;

	@Column(name = "amount")
	private BigDecimal amount;

	public Transaction() {
		super();
	}

	public Transaction(Account account, String description, String type, Instant transactionTime, BigDecimal amount) {
		super();
		this.account = account;
		this.description = description;
		this.type = type;
		this.transactionTime = transactionTime;
		this.amount = amount;
	}

	public Transaction(Long id, Account account, String description, String type, Instant transactionTime,
			BigDecimal amount) {
		super();
		this.id = id;
		this.account = account;
		this.description = description;
		this.type = type;
		this.transactionTime = transactionTime;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Instant getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Instant transactionTime) {
		this.transactionTime = transactionTime;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
