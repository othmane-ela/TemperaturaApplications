package com.ehei.TemperaturaApplication.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehei.TemperaturaApplication.entities.Node;


@Repository
public interface NodeRepository  extends CrudRepository<Node,Long>{

}
