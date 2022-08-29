package com.dbs.team3.model;

public class MissingFields {
	
	String messageCode;
	double amount;
	String status;
	
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public MissingFields(String messageCode, double amount, String status) {
		super();
		this.messageCode = messageCode;
		this.amount = amount;
		this.status = status;
	}
	public MissingFields() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MissingFields [messageCode=" + messageCode + ", amount=" + amount + ", status=" + status + "]";
	}
	
	

}
