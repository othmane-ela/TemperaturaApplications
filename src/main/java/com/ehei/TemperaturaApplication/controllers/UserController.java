package com.ehei.TemperaturaApplication.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehei.TemperaturaApplication.DTO.response.UserInfo;
import com.ehei.TemperaturaApplication.entities.User;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserInfo(Principal user){
		
		User userObj=(User) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(userObj.getFirstName());
		userInfo.setLastName(userObj.getLastName());
		userInfo.setRoles(userObj.getAuthorities().toArray());
		
		
		return ResponseEntity.ok(userInfo);
		
		
		
	}

}
