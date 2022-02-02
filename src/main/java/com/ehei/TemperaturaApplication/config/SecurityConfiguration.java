package com.ehei.TemperaturaApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ehei.TemperaturaApplication.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserService userService;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
			http.authorizeHttpRequests((request)->request.antMatchers("/**").permitAll().anyRequest().authenticated()).httpBasic();
			
			http.formLogin();
		
			http.csrf().disable().headers().frameOptions().disable();
			
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userService).passwordEncoder(PasswordEncoder());
	}

	
	@Bean 
	public PasswordEncoder PasswordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
}
