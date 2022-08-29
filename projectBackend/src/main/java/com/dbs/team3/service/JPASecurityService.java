package com.dbs.team3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbs.team3.model.User;
import com.dbs.team3.repo.JPASecurityRepo;

@Service
public class JPASecurityService implements UserDetailsService {
	
	@Autowired
	JPASecurityRepo jpaSecurityRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = jpaSecurityRepo.findByUsername(username);
		JPAUserDetailsService jpaUserDetails = new JPAUserDetailsService(user);
		return jpaUserDetails;
	}
	
	
	
}
