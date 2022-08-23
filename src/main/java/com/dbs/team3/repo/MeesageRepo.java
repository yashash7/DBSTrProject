package com.dbs.team3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.team3.model.Message;

public interface MeesageRepo extends JpaRepository<Message, String> {
	
	
}
