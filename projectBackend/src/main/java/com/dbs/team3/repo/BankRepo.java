package com.dbs.team3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.team3.model.Bank;

public interface BankRepo extends JpaRepository<Bank, String> {

	Bank findByBic(String bic);

}
