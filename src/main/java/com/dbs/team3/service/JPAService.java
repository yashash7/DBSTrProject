package com.dbs.team3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.team3.model.Sender;
import com.dbs.team3.repo.ReceiverRepo;
import com.dbs.team3.repo.SenderRepo;
@Service
public class JPAService {

	@Autowired
	SenderRepo senderRepo;
	
	@Autowired
	ReceiverRepo receiverRepo;
	
	public String validateSender(String no) {
		
		Sender sender = senderRepo.findByaccno(no);
		
		if(sender != null) return null;
		else return "Transcation failed";
			
		
	}

}
