package com.dbs.team3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.team3.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	Customer findByaccno(String accno);

}
