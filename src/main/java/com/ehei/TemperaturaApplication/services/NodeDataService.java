package com.ehei.TemperaturaApplication.services;

import java.util.List;

import com.ehei.TemperaturaApplication.entities.NodeData;

public interface NodeDataService {
	
	 List< NodeData> getNodesData();

	 NodeData getNodeDataById(Long id);

	  NodeData insert( NodeData  nodeData);

	  void updateNodeData(Long id,  NodeData  nodeData);

	  void deleteNodeData(Long nodeDataID);


}
