package com.poc.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "master_account")
@Entity
public class MasterAccount {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
		
	@Column(name = "account_number")
	private String accountNumber;	
	
	private float balance;
	
	@ManyToOne
    @JoinColumn(name = "currency_id")
	private Currency currency;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private UserInfo userInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}	
	
}
