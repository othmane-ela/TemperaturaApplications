package com.ehei.TemperaturaApplication.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehei.TemperaturaApplication.DTO.request.NodeDTO;
import com.ehei.TemperaturaApplication.DTO.request.NodeDataDTO;
import com.ehei.TemperaturaApplication.entities.Environment;
import com.ehei.TemperaturaApplication.entities.Node;
import com.ehei.TemperaturaApplication.entities.NodeData;
import com.ehei.TemperaturaApplication.services.EnvironmentService;
import com.ehei.TemperaturaApplication.services.NodeDataService;
import com.ehei.TemperaturaApplication.services.NodeService;

@RestController
@RequestMapping("/api/v1/node")
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private NodeDataService nodeDataService;

	@Autowired
	private EnvironmentService environmentService;
	
	@Autowired
	private ModelMapper modelMapper;
		
	
	@GetMapping({"/"})
	public ResponseEntity<List<Node>>  getAllNodes(){
		List<Node> nodes = nodeService.getNodes();
		return new ResponseEntity<>(nodes,HttpStatus.OK);
	}
	
	
	@PostMapping({"/{environmentId}"})
    public ResponseEntity<NodeDTO> saveNode(@RequestBody NodeDTO dto,@PathVariable("environmentId") Long environmentId) {
    	Node node = modelMapper.map(dto,Node.class);

    	Environment environmentFromDb = environmentService.getEnvironmentById(environmentId);
    	node.setEnvironment(environmentFromDb);
    	
    	nodeService.insert(node);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
	
	@PostMapping({"/data"})
	public ResponseEntity<NodeData>  inserNodeData(@RequestBody NodeDataDTO dto){

		NodeData nodeData = modelMapper.map(dto,NodeData.class);
		Node node = nodeService.getNodeById(dto.getNode());
		nodeData.setNode(node);
		nodeDataService.insert(nodeData);
		System.out.println(nodeData);		
		return  new ResponseEntity<>(nodeData,HttpStatus.CREATED);
	
	}
	
	
	@GetMapping({"/data/{nodeId}"})
	public ResponseEntity<Node> nodeData(@PathVariable("nodeId") Long nodeId){
			Node node = nodeService.getNodeById(nodeId);
			return new ResponseEntity<>(node,HttpStatus.OK);
		
	}


}
