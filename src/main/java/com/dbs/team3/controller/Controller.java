package com.dbs.team3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.team3.service.JPAService;

@RestController
public class Controller {
	
	@Autowired
	JPAService jpaService;
	
	
	@RequestMapping("/")
	public String home() {
		return "***   WELCOME TO TEAM3's REST API SERVICES   ***";
	}
	
	@GetMapping("/sender/validation")
	public String validationSender(@RequestParam String no) {
		return jpaService.validateSender(no);
	}
	
	@RequestMapping("/user")
	public String userPage() {
		return "This is User page authorized to any type of user";
	}
	
	@RequestMapping("/employee")
	public String employeePage() {
		return "Welcome to Employee Page";
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
