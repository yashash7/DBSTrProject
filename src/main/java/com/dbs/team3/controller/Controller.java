package com.dbs.team3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.team3.model.Customer;
import com.dbs.team3.model.MissingFields;
import com.dbs.team3.service.JPAService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
	
	@Autowired
	JPAService jpaService;
	
	@RequestMapping("/validateSender")
	public String validateSender(@RequestParam String accno) {
		return jpaService.validateSender(accno);
	}
	@RequestMapping("/getSender") //Will be done only if sender is validated
	public Customer getSender(@RequestParam String accno) {
		return jpaService.getCustomer(accno);
	}
	
	@GetMapping("/receiverNameCheck")
	public String checkReceiverNameInOFAC(@RequestParam String name) {
		return jpaService.checkReceiverNameInOFAC(name);
	}
	

	@RequestMapping("/receiverBank")
	public String getBankByBic(@RequestParam String bic) {
		return jpaService.getBankByBic(bic);
	}

	@RequestMapping("/transaction")
	public void doTransaction(@RequestParam List<String> missing) {
		jpaService.doTransaction(missing);
	}

}
