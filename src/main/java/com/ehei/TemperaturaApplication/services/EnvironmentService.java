package com.ehei.TemperaturaApplication.services;

import java.util.List;

import com.ehei.TemperaturaApplication.entities.Environment;

public interface EnvironmentService {
	
	 List< Environment> getEnvironments();

	 Environment getEnvironmentById(Long id);

	  Environment insert( Environment  environment);

	  void updateEnvironment(Long id,  Environment  environment);

	  void deleteEnvironment(Long environmentId);

}
