package com.ehei.TemperaturaApplication.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehei.TemperaturaApplication.entities.Environment;
import com.ehei.TemperaturaApplication.repositories.EnvironmentRepository;
import com.ehei.TemperaturaApplication.services.EnvironmentService;

@Service
public class EnvironmentServiceImpl  implements EnvironmentService{
	
	@Autowired
	private EnvironmentRepository environmentRepository;

	@Override
	public List<Environment> getEnvironments() {
	
		  List<Environment> environments = new ArrayList<>();
		  environmentRepository.findAll().forEach(environments::add);
	      return environments;
		
	}

	@Override
	public Environment getEnvironmentById(Long id) {
		  return environmentRepository.findById(id).get();
	}

	@Override
	public Environment insert(Environment environment) {
		return environmentRepository.save(environment);
	}

	@Override
	public void updateEnvironment(Long id, Environment environment) {
		
			Environment environmentFromDb = environmentRepository.findById(id).get();
	        System.out.println(environmentFromDb.toString());
	        environmentFromDb.setName(environment.getName());
	        environmentFromDb.setDescription(environment.getDescription());
	        environmentFromDb.setDescription(environment.getDescription());
	        environmentFromDb.setMaxHumidity(environment.getMaxHumidity());
	        environmentFromDb.setMinHumidity(environment.getMaxHumidity());
	        environmentFromDb.setMaxTemperature(environment.getMaxTemperature());
	        environmentFromDb.setMinTemperature(environment.getMinTemperature());
	        environmentRepository.save(environmentFromDb);
		
	}

	@Override
	public void deleteEnvironment(Long environmentId) {
		environmentRepository.deleteById(environmentId);
		
	}

}
