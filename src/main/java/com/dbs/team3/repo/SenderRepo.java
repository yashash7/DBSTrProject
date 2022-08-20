package com.dbs.team3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.team3.model.Sender;

public interface SenderRepo extends JpaRepository<Sender, Integer>{

	Sender findByaccno(String no);

}
