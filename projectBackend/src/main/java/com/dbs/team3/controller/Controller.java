package com.dbs.team3.controller;

import java.util.Date;
//import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.team3.model.Customer;
import com.dbs.team3.model.Transaction;
import com.dbs.team3.service.JPAService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
	
	@Autowired
	JPAService jpaService;
	
	
	@GetMapping("/auth") //API End Point for Angular Login Page Authentication
    public String auth(@RequestParam String username, String password) {
        return jpaService.login(username,password);
    }
	
	@RequestMapping("/validateSender") // API End Point for Validating Sender with Sender Account No
	public String validateSender(@RequestParam String accno) {
		return jpaService.validateSender(accno);
	}
	
	//API End Point for getting sender; Will be done only if sender is validated
	@RequestMapping("/getSender") 
	public Customer getSender(@RequestParam String accno) {
		return jpaService.getCustomer(accno);
	}
	
	// API End Point for checking Receiver Name in Terrr List
	@GetMapping("/receiverNameCheck") 
	public String checkReceiverNameInOFAC(@RequestParam String name) {
		return jpaService.checkReceiverNameInOFAC(name);
	}
	

	// API End Point for Auto Filling Bank Name by BIC Code
	@RequestMapping("/receiverBank")
	public String getBankByBic(@RequestParam String bic) {
		return jpaService.getBankByBic(bic);
	}

	// API End Point for performing Transaction
	@RequestMapping("/transaction")
	public List<Transaction> doTransaction(@RequestParam List<String> missing) {
		return jpaService.doTransaction(missing);
	}
	
	// API End Point for getting Transaction List
	@GetMapping("/transbyacc")
	public List<Transaction> transbyacc(@RequestParam String accno) {
		return jpaService.transbyacc(accno);
	}

}
