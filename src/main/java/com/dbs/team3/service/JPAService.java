package com.dbs.team3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.team3.model.Bank;
import com.dbs.team3.model.Customer;
import com.dbs.team3.model.MissingFields;
import com.dbs.team3.model.RawData;
import com.dbs.team3.model.Transaction;
import com.dbs.team3.repo.BankRepo;
import com.dbs.team3.repo.CustomerRepo;
import com.dbs.team3.repo.TransactionRepo;
@Service
public class JPAService {
	
	@Autowired
	TransactionRepo transactionRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	BankRepo bankRepo;
	
	Transaction transaction = new Transaction();
	Customer sender = new Customer();
	static int trId = 101;
	
	public void doTransaction(List<String> missing) {
		//Setting messageCode
		transaction.setReceiverAccNo(missing.get(3));
		transaction.setMessageCode(missing.get(0));
		transaction.setMessageInstruction(RawData.messageMap.get(transaction.getMessageCode()));
		transaction.setAmount(Double.parseDouble(missing.get(1)));
		transaction.setTransactionId(trId++);
		if(transaction.getStatus()=="insufficientFunds" || transaction.getStatus()=="receiverInTerror") {
			transaction.setStatus(RawData.statusMap.get(missing.get(2)));
			transactionRepo.save(transaction);
			System.out.println(transaction.toString());
		}
		else {
			transaction.setStatus(RawData.statusMap.get(missing.get(2)));
			sender.setBalance(sender.getBalance()-transaction.getAmount());
			customerRepo.save(sender);
			transactionRepo.save(transaction);
			System.out.println(transaction.toString());
		}
	}
	
	// Sender Functions
	public Customer getCustomer(String accno) {
		return customerRepo.findByaccno(accno);
	}
	public String validateSender(String accno) {
		transaction.setSenderAccNo(accno);
		sender = customerRepo.findByaccno(accno);
		if(sender!=null) {
			transaction.setSenderName(sender.getName());
			return sender.getName();
		}
		else return "RED";
	}
	
	// Receiver Functions
	public String getBankByBic(String bic) {
		Bank bank = bankRepo.findByBic(bic);
		System.out.println(bank);
		transaction.setReceiverBIC(bic);
		if(bank!=null) {
			transaction.setBankName(bank.getBank());
			return bank.getBank();
		}
		else return "RED";
//		return null;
	}	
	public String checkReceiverNameInOFAC(String receiverName) {
		transaction.setReceiverName(receiverName);
		int indexOfReceiverInOFAC = RawData.ofacList.indexOf(receiverName);
		if(indexOfReceiverInOFAC>-1) {
			return "RED";
		}
		return "GREEN";
//		if(indexOfReceiverInOFAC>-1) return "Receiver Present in OFAC List -> Abort Transaction!";
//		return "Receiver valid, Proceed to the transaction";
	}

}
