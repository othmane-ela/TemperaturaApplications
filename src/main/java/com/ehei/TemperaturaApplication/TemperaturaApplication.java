package com.ehei.TemperaturaApplication;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ehei.TemperaturaApplication.entities.Authority;
import com.ehei.TemperaturaApplication.entities.User;
import com.ehei.TemperaturaApplication.repositories.UserDetailRepository;

@SpringBootApplication
public class TemperaturaApplication {
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	public static void main(String[] args) {
		SpringApplication.run(TemperaturaApplication.class, args);
		
	}
	
	@PostConstruct
	protected void init() {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(createAuthority("USER","USER_ROLE"));
		authorities.add(createAuthority("ADMIN","ADMIN_ROLE"));
		
		User user = new User();
		user.setUsername("othmane.org");
		user.setFirstName("othmane");
		user.setLastName("K");
		user.setPassword(passwordEncoder.encode("othmane@org"));
		user.setEnabled(true);
	   user.setAuthorities(authorities);
		userDetailRepository.save(user);
	}
	
	
	private Authority createAuthority(String roleCode,String roleDescription) {
			Authority authority = new Authority();
			authority.setRoleCode(roleCode);
			authority.setRoleDescription(roleDescription);
			return authority;
	}

}
