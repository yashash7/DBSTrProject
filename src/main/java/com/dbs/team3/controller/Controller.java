package com.dbs.team3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.team3.model.Customer;
import com.dbs.team3.service.JPAService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
	
	@Autowired
	JPAService jpaService;
	
	@RequestMapping("/sender/validation")
	public String validationSender(@RequestParam String accno) {
		return jpaService.validateSender(accno);
	}
	@RequestMapping("/senderName")
	public String getSenderName(@RequestParam String accno) {
		return getSenderName(accno);
	}
	
	@GetMapping("/receiver/validation")
	public String checkReceiverNameInOFAC(@RequestParam String name) {
		return jpaService.checkReceiverNameInOFAC(name);
	}
	

	@RequestMapping("/receiver/bank")
	public String getBankByBic(@RequestParam String bic) {
		return jpaService.getBankByBic(bic);
	}
	
	@RequestMapping("/checkBalance")
	public String checkSenderBalance(@RequestParam String accno, double amount) {
		return jpaService.checkSenderBalance(accno, amount);
	}
	
	

}
