package com.dbs.team3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.team3.model.Bank;
import com.dbs.team3.model.Customer;
import com.dbs.team3.repo.BankRepo;
import com.dbs.team3.repo.CustomerRepo;
@Service
public class JPAService {
	
	static List<String> ofacList = new ArrayList<String>();
	static {
		ofacList.add("ANUPAMA N");
		ofacList.add("AJAY SHAMU KUMAR");
		ofacList.add("B A SANTOSH");
		ofacList.add("ANIL KUMAR NAGARAJ");
		ofacList.add("AKSHAYA WEALTH MANAGEMENT (P) LTD");
	}

	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	BankRepo bankRepo;
	
	public Customer getCustomer(String accno) {
		Customer customer = customerRepo.findByaccno(accno);
		return customer;
	}
	public Bank getBankByBic(String bic) {
		Bank bank = bankRepo.findByBic(bic);
		return bank;
	}
	
	public String validateSender(String accno) {
		Customer sender = getCustomer(accno);
		System.out.println(sender);
		if(sender != null) return "GREEN";
		else return "RED";
//		if(sender != null) return "Sender Valid, Proceed to next step";
//		else return "Sender Invalid -> Abort Transaction!";
	}
	
	
	public String checkReceiverNameInOFAC(String receiverName) {
		int indexOfReceiverInOFAC = ofacList.indexOf(receiverName);
		if(indexOfReceiverInOFAC>-1) return "Receiver Present in OFAC List -> Abort Transaction!";
		return "Receiver valid, Proceed to the transaction";
	}
	
	public String transaction(Customer sender, Customer receiver, double amount) {
		String msg = "";
		try {
			sender.setBalance(sender.getBalance()-amount);
//			receiver.setBalance(receiver.getBalance()+amount);
			customerRepo.save(sender);
//			customerRepo.save(receiver);
			msg = "Transaction Success";
			
		} catch(Exception e) {
			msg = "Transaction Failed";
		}
		return msg;
	}

}
