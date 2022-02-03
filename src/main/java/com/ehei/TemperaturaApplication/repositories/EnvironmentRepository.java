package com.ehei.TemperaturaApplication.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehei.TemperaturaApplication.entities.Environment;

@Repository
public interface EnvironmentRepository extends CrudRepository< Environment, Long> {

}
