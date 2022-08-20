package com.dbs.team3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.team3.service.JPAService;

@RestController
public class Controller {
	
	@Autowired
	JPAService jpaService;
	
	
	@GetMapping("/sender/validation")
	public String validationSender(@RequestParam String no) {
		return jpaService.validateSender(no);
	}

}
