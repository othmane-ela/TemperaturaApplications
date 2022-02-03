package com.ehei.TemperaturaApplication.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehei.TemperaturaApplication.entities.Node;
import com.ehei.TemperaturaApplication.repositories.NodeRepository;
import com.ehei.TemperaturaApplication.services.NodeService;



@Service
public class NodeServiceImpl  implements NodeService{
	
	@Autowired
	private NodeRepository nodeRepository;

	@Override
	public List<Node> getNodes() {
		  List<Node> nodes = new ArrayList<>();
		  nodeRepository.findAll().forEach(nodes::add);
	      return nodes;
	}
	
	

	@Override
	public Node getNodeById(Long id) {
		  return nodeRepository.findById(id).get();
	}

	@Override
	public Node insert(Node node) {
		  return nodeRepository.save(node);
	}

	
	 @Override
	    public void updateNode(Long id, Node node) {
	        Node nodeFromDb = nodeRepository.findById(id).get();
	        System.out.println(nodeFromDb.toString());
	        nodeFromDb.setName(node.getName());
	        nodeFromDb.setPosition(node.getPosition());	  
	        nodeRepository.save(nodeFromDb);
	    }

	    @Override
	    public void deleteNode(Long nodeId) {
	    	nodeRepository.deleteById(nodeId);
	    }

}
