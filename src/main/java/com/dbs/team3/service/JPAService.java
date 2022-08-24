package com.dbs.team3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.team3.model.Bank;
import com.dbs.team3.model.Customer;
import com.dbs.team3.model.RawData;
import com.dbs.team3.model.Transaction;
import com.dbs.team3.repo.BankRepo;
import com.dbs.team3.repo.CustomerRepo;
import com.dbs.team3.repo.TransactionRepo;
@Service
public class JPAService {
	
	@Autowired
	TransactionRepo transactionRepo;
	
	Transaction transaction=new Transaction();
	

	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	BankRepo bankRepo;
	
	public Customer getCustomer(String accno) {
		Customer customer = customerRepo.findByaccno(accno);
		return customer;
	}
	public String getBankByBic(String bic) {
		Bank bank = bankRepo.findByBic(bic);
		transaction.setReceiverBIC(bic);
		transaction.setBankName(bank.getBank());
		if(bank!=null) return bank.getBank();
		else return "RED";
	}
	
	public String validateSender(String accno) {
		transaction.setSenderAccNo(accno);
		Customer sender = getCustomer(accno);
		System.out.println(sender);
		if(sender != null) {
			return "GREEN";
		}
		else {
			transaction.setStatus("Failed due to invalid sender!!!!");
			return "RED";
		}
//		if(sender != null) return "Sender Valid, Proceed to next step";
//		else return "Sender Invalid -> Abort Transaction!";
	}
	
	
	public String checkReceiverNameInOFAC(String receiverName) {
		transaction.setReceiverName(receiverName);
		int indexOfReceiverInOFAC = RawData.ofacList.indexOf(receiverName);
		if(indexOfReceiverInOFAC>-1) {
			transaction.setStatus("Failed!. Receiver present in terror group. ");
			return "RED";
		}
		return "GREEN";
//		if(indexOfReceiverInOFAC>-1) return "Receiver Present in OFAC List -> Abort Transaction!";
//		return "Receiver valid, Proceed to the transaction";
	}
	
	public String checkSenderBalance(String accno, double amount) {
		// TODO Auto-generated method stub
		Customer senderForBalance = getCustomer(accno);
		if(senderForBalance.getOverdraft()=="yes")
			return "GREEN";
		else if(senderForBalance.getBalance()>=amount)
			return "GREEN"; 
		return "RED";
		
	}
	
	public String getSenderName(String accno) {
		Customer senderForName = getCustomer(accno);
		transaction.setSenderName(senderForName.getName());
		return senderForName.getName();
	}

}
