package com.ehei.TemperaturaApplication.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehei.TemperaturaApplication.DTO.request.EnvironmentDTO;
import com.ehei.TemperaturaApplication.entities.Environment;
import com.ehei.TemperaturaApplication.services.EnvironmentService;


@RestController
@RequestMapping("/api/v1/environment")
public class EnvironmentController {
	
		@Autowired
		private EnvironmentService environmentService;
		
		@Autowired
		private ModelMapper modelMapper;
	
			
		@GetMapping({"/"})
	    public ResponseEntity<List<Environment>> getAllEnvironments() {
	        List<Environment> environments = environmentService.getEnvironments();
	        return new ResponseEntity<>(environments, HttpStatus.OK);
	    }
	    

		@PostMapping({"/"})
	    public ResponseEntity<Environment> saveEnvironment(@RequestBody EnvironmentDTO dto) {
	    	Environment environment = modelMapper.map(dto,Environment.class);
	    	Environment environmentFromDb = environmentService.insert(environment);
	        return new ResponseEntity<>(environmentFromDb,HttpStatus.CREATED);
	    }
	
	    @GetMapping({"/{environmenttId}"})
	    public ResponseEntity<Environment> getEnvironment(@PathVariable Long environmentId) {
	        return new ResponseEntity<>(environmentService.getEnvironmentById(environmentId), HttpStatus.OK);
	    }

	    
	
	    @PutMapping({"/{environmentId}"})
	    public ResponseEntity<Environment> updateEnvironment(@PathVariable("environmentId") Long environmentId, @RequestBody Environment environment) {
	    	environmentService.updateEnvironment(environmentId, environment);
	        return new ResponseEntity<>(environmentService.getEnvironmentById(environmentId), HttpStatus.OK);
	    }
	   
	    
	 
	    @DeleteMapping({"/{environmentId}"})
	    public ResponseEntity<Environment> deleteEnvironment(@PathVariable("environmentId") Long environmentId) {
	    	environmentService.deleteEnvironment(environmentId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	

}



