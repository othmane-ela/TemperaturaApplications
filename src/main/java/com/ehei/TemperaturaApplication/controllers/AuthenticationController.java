package com.ehei.TemperaturaApplication.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehei.TemperaturaApplication.DTO.request.LoginDTO;
import com.ehei.TemperaturaApplication.DTO.request.SignupDTO;
import com.ehei.TemperaturaApplication.DTO.response.LoginResponseDTO;
import com.ehei.TemperaturaApplication.DTO.response.UserInfo;
import com.ehei.TemperaturaApplication.config.JWTTokenHelper;
import com.ehei.TemperaturaApplication.entities.Authority;
import com.ehei.TemperaturaApplication.entities.User;
import com.ehei.TemperaturaApplication.repositories.UserDetailRepository;

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
	
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/auth/signin")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO,HttpServletResponse res) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDTO.getUsername(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user=(User)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		
		LoginResponseDTO response=new LoginResponseDTO();
		response.setToken(jwtToken);
		
	     final Cookie cookie = new Cookie("auth", jwtToken);
			cookie.setHttpOnly(true);
			cookie.setMaxAge(Integer.MAX_VALUE);
			cookie.setPath("/");
			res.addCookie(cookie);
		
		return ResponseEntity.ok(response);
	}
	
	
	
	@PostMapping("/auth/signup")
		public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO){
		
			User user = modelMapper.map(signupDTO, User.class);
			
			List<Authority> authorities = new ArrayList<>();
			authorities.add(createAuthority("USER","USER_ROLE"));
			user.setEnabled(true);
		   user.setAuthorities(authorities);
		   user.setPassword(passwordEncoder.encode(user.getPassword()));
		   userDetailRepository.save(user);
				
		return ResponseEntity.ok("Account Created");
			
		}
	
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority = new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
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