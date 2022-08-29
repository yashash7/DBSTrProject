package com.dbs.team3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.team3.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	List<Transaction> findBysenderAccNo(String accno);

}
