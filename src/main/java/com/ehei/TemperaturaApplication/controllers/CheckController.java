package com.ehei.TemperaturaApplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
	
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	

}
