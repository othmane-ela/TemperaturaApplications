package com.ehei.TemperaturaApplication.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehei.TemperaturaApplication.entities.NodeData;
import com.ehei.TemperaturaApplication.repositories.NodeDataRepository;
import com.ehei.TemperaturaApplication.services.NodeDataService;


@Service
public class NodeDataServiceImpl implements NodeDataService {
	
	@Autowired
	private NodeDataRepository nodeDataRepository;

	@Override
	public List<NodeData> getNodesData() {
		  List<NodeData> nodesData = new ArrayList<>();
		  nodeDataRepository.findAll().forEach(nodesData::add);
	      return nodesData;
	}

	@Override
	public NodeData getNodeDataById(Long id) {
		  return nodeDataRepository.findById(id).get();
	}

	@Override
	public NodeData insert(NodeData nodeData) {
		  return nodeDataRepository.save(nodeData);
	}

	@Override
	public void updateNodeData(Long id, NodeData nodeData) {

	}

	@Override
	public void deleteNodeData(Long nodeDataID) {
		nodeDataRepository.deleteById(nodeDataID);
		
	}

}
