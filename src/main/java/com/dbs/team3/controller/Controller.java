package com.dbs.team3.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	
	@RequestMapping("/")
	public String home() {
		System.out.println("Home Called");
		return "***   WELCOME TO TEAM3's REST API SERVICES   ***";
	}
	
	@RequestMapping("/sender/validation")
	public String validationSender(@RequestParam String accno) {
		return jpaService.validateSender(accno);
	}
	@RequestMapping("/senderName")
	public String getSenderName(@RequestParam String accno) {
		Customer senderForName = jpaService.getCustomer(accno);
		System.out.println("AutofillSenderName: "+senderForName.getName());
		return senderForName.getName();
	}
	
	@GetMapping("/receiver/validation")
	public String checkReceiverNameInOFAC(@RequestParam String name) {
		return jpaService.checkReceiverNameInOFAC(name);
	}
	
	@RequestMapping("/transaction")
	public String doTransaction(@RequestParam String senderAccno, String receiverAccno, double amount) {
		Customer sender = jpaService.getCustomer(senderAccno);
		Customer receiver = jpaService.getCustomer(receiverAccno);
		return jpaService.transaction(sender, receiver, amount);
	}
	
	@RequestMapping("/user")
	public String userPage() {
		return "This is User page authorized to any type of user";
	}
	
	@RequestMapping("/employee")
	public String employeePage() {
		return "Welcome to Employee Page";
	}
	
	@RequestMapping("/receiver/bank")
	public String getBankByBic(@RequestParam String bic) {
		return jpaService.getBankByBic(bic);
	}
	
	
	@RequestMapping("/admin")
	public List<String> allEndpoints() {
		List<String> endPointsList = new ArrayList<String>();
		endPointsList.add("/ -> Root Page [No Auth]");
		endPointsList.add("/user -> User Page [Authorized to USER,EMPLOYEE,ADMIN]");
		endPointsList.add("/employee -> Employee Page [Authorized to EMPLOYEE only]");
		endPointsList.add("/admin -> Admin Page [Authorized to ADMIN only]");
		endPointsList.add("/sender/validation -> Authorized to  EMPLOYEE/ADMIN to  Validate the Sender; give ?no=accno as request parameter");
		return endPointsList;
	}

}
