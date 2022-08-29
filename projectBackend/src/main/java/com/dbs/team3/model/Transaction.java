package com.dbs.team3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	int transactionId;
	String senderAccNo;
	String senderName;
	String receiverAccNo;
	String receiverName;
	String receiverBIC;  
	String bankName;
	double amount;
	String messageCode;
	String messageInstruction;
	String status; // pass or fail
	String timeStamp;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getSenderAccNo() {
		return senderAccNo;
	}
	public void setSenderAccNo(String senderAccNo) {
		this.senderAccNo = senderAccNo;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverBIC() {
		return receiverBIC;
	}
	public void setReceiverBIC(String receiverBIC) {
		this.receiverBIC = receiverBIC;
	}
	public String getReceiverAccNo() {
		return receiverAccNo;
	}
	public void setReceiverAccNo(String receiverAccNo) {
		this.receiverAccNo = receiverAccNo;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getMessageInstruction() {
		return messageInstruction;
	}
	public void setMessageInstruction(String messageInstruction) {
		this.messageInstruction = messageInstruction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int transactionId, String senderAccNo, String senderName, String receiverAccNo,
			String receiverName, String receiverBIC, String bankName, double amount, String messageCode,
			String messageInstruction, String status, String timeStamp) {
		super();
		this.transactionId = transactionId;
		this.senderAccNo = senderAccNo;
		this.senderName = senderName;
		this.receiverAccNo = receiverAccNo;
		this.receiverName = receiverName;
		this.receiverBIC = receiverBIC;
		this.bankName = bankName;
		this.amount = amount;
		this.messageCode = messageCode;
		this.messageInstruction = messageInstruction;
		this.status = status;
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", senderAccNo=" + senderAccNo + ", senderName="
				+ senderName + ", receiverAccNo=" + receiverAccNo + ", receiverName=" + receiverName + ", receiverBIC="
				+ receiverBIC + ", bankName=" + bankName + ", amount=" + amount + ", messageCode=" + messageCode
				+ ", messageInstruction=" + messageInstruction + ", status=" + status + ", timeStamp=" + timeStamp
				+ "]";
	}

	
		
	
}
