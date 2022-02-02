package com.ehei.TemperaturaApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ehei.TemperaturaApplication.entities.User;
import com.ehei.TemperaturaApplication.repositories.UserDetailRepository;


@Service
public class UserService implements UserDetailsService {

	
	@Autowired
	 UserDetailRepository userDetailRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDetailRepository.findByUserName(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("user Not Foud with userName "+username);
		}
		
		return user;
		
	}

}
