package com.dbs.team3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.team3.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
