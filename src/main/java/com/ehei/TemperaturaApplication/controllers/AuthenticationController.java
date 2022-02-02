package com.ehei.TemperaturaApplication.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehei.TemperaturaApplication.DTO.LoginDTO;
import com.ehei.TemperaturaApplication.DTO.LoginResponseDTO;
import com.ehei.TemperaturaApplication.DTO.UserInfo;
import com.ehei.TemperaturaApplication.config.JWTTokenHelper;
import com.ehei.TemperaturaApplication.entities.User;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDTO.getUsername(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user=(User)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		
		LoginResponseDTO response=new LoginResponseDTO();
		response.setToken(jwtToken);
		

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/auth/user")
	public ResponseEntity<?> getUserInfo(Principal user){
		
		User userObj=(User) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(userObj.getFirstName());
		userInfo.setLastName(userObj.getLastName());
		userInfo.setRoles(userObj.getAuthorities().toArray());
		
		
		return ResponseEntity.ok(userInfo);
		
		
		
	}
}