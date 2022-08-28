package com.dbs.team3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.team3.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByusername(String username);

}
