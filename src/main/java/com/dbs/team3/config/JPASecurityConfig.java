package com.dbs.team3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dbs.team3.service.JPASecurityService;

@Configuration
@EnableWebSecurity
public class JPASecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JPASecurityService jpasecurityService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(jpasecurityService);
		
	}
		
	//Authorization
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/validateSender", "/getSender", "/receiverNameCheck", "/receiverBank", "/transaction", "/auth", "/transbyacc", "/date").permitAll()
//		.antMatchers("/user").hasAnyAuthority("ADMIN", "USER", "EMPLOYEE")
//		.antMatchers("/sender/validation").hasAnyAuthority("EMPLOYEE", "ADMIN")
//		.antMatchers("/receiver/validation").hasAnyAuthority("EMPLOYEE", "ADMIN")
//		.antMatchers("/admin").hasAuthority("ADMIN")
		.and()
		.headers().frameOptions().disable()
		.and()
		.formLogin();
		
	}
		
		@Bean
		public PasswordEncoder getPasswordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
}
