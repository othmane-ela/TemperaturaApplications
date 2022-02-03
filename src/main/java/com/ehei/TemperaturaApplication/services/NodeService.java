package com.ehei.TemperaturaApplication.services;

import java.util.List;

import com.ehei.TemperaturaApplication.entities.Node;

public interface NodeService {
	
	 List< Node> getNodes();

	 Node getNodeById(Long id);

	  Node insert( Node  node);

	  void updateNode(Long id,  Node  node);

	  void deleteNode(Long nodeId);


}
