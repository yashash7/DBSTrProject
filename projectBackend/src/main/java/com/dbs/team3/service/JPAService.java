package com.dbs.team3.service;

//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbs.team3.model.Bank;
import com.dbs.team3.model.Customer;
//import com.dbs.team3.model.MissingFields;
import com.dbs.team3.model.RawData;
import com.dbs.team3.model.Transaction;
import com.dbs.team3.model.User;
import com.dbs.team3.repo.BankRepo;
import com.dbs.team3.repo.CustomerRepo;
import com.dbs.team3.repo.TransactionRepo;
import com.dbs.team3.repo.UserRepo;
@Service
public class JPAService {
	
	@Autowired
	TransactionRepo transactionRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	BankRepo bankRepo;
	
	@Autowired
	UserRepo userRepo;
	
	Transaction transaction = new Transaction();
	Customer sender = new Customer();
	static int trId = 101;
	String status;
	
	public List<Transaction> doTransaction(List<String> missing) {
		Date javaTimeStamp = new Date();
		transaction.setTimeStamp(javaTimeStamp.toInstant().toString());
		//Setting messageCode
		transaction.setReceiverAccNo(missing.get(3));
		transaction.setMessageCode(missing.get(0));
		transaction.setMessageInstruction(RawData.messageMap.get(transaction.getMessageCode()));
		transaction.setAmount(Double.parseDouble(missing.get(1)));
		transaction.setTransactionId(trId++);
		transaction.setStatus(RawData.statusMap.get(missing.get(2)));
		System.out.println("Transaction Obj "+transaction.toString());
		System.out.println("StatusFlagSpring: "+missing.get(2));
		if(missing.get(2).equalsIgnoreCase("insufficientFunds") || missing.get(2).equalsIgnoreCase("receiverInTerror")) {
			transactionRepo.save(transaction);
			System.out.println(transaction.toString());
		}
		if(missing.get(2).equalsIgnoreCase("success")) {
			sender.setBalance(sender.getBalance()-transaction.getAmount());
			System.out.println(sender.toString());
			transactionRepo.save(transaction);
			customerRepo.save(sender);
			System.out.println(transaction.toString());
		}
		return transactionRepo.findBysenderAccNo(transaction.getSenderAccNo());
	}
	
	// Sender Functions
	public Customer getCustomer(String accno) {
		Customer senderC = customerRepo.findById(accno).orElse(new Customer());
		this.sender = senderC;
		return senderC;
		
	}
	public String validateSender(String accno) {
		transaction.setSenderAccNo(accno);
		sender = customerRepo.findById(accno).orElse(new Customer());
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

	public String login(String username, String password) {
		User user = userRepo.findByusername(username);
        if(user!=null) {
            if(user.getPassword().contentEquals(password))
                return "postive";
            else return "negative";
        }
        else return "negative";
	}

	public List<Transaction> transbyacc(@RequestParam String accno) {
		// TODO Auto-generated method stub
		return transactionRepo.findBysenderAccNo(accno);
		
	}

}
