package com.dbs.team3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	int transactionId;
	String customerId;
	String senderAccNo;
	String receiverBIC;
	String receiverAccNo;
	String receiverName;  
	String status; // pass or fail
	String messageCode;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSenderAccNo() {
		return senderAccNo;
	}
	public void setSenderAccNo(String senderAccNo) {
		this.senderAccNo = senderAccNo;
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
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", customerId=" + customerId + ", senderAccNo="
				+ senderAccNo + ", receiverBIC=" + receiverBIC + ", receiverAccNo=" + receiverAccNo + ", receiverName="
				+ receiverName + ", status=" + status + ", messageCode=" + messageCode + "]";
	}
	public Transaction(int transactionId, String customerId, String senderAccNo, String receiverBIC,
			String receiverAccNo, String receiverName, String status, String messageCode) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.senderAccNo = senderAccNo;
		this.receiverBIC = receiverBIC;
		this.receiverAccNo = receiverAccNo;
		this.receiverName = receiverName;
		this.status = status;
		this.messageCode = messageCode;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
