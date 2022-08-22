package com.dbs.team3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	String accno;
	String name;
	double balance;
	String overdraft;
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getOverdraft() {
		return overdraft;
	}
	public void setOverdraft(String overdraft) {
		this.overdraft = overdraft;
	}
	@Override
	public String toString() {
		return "Sender [accno=" + accno + ", name=" + name + ", balance=" + balance + ", overdraft=" + overdraft + "]";
	}
	public Customer(String accno, String name, double balance, String overdraft) {
		super();
		this.accno = accno;
		this.name = name;
		this.balance = balance;
		this.overdraft = overdraft;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
}
