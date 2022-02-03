package com.ehei.TemperaturaApplication.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehei.TemperaturaApplication.entities.NodeData;

@Repository
public interface NodeDataRepository extends  CrudRepository<NodeData,Long> {

}
