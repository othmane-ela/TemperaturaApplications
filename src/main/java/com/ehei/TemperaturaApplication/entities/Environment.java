package com.ehei.TemperaturaApplication.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table(name="ENVIRONMENT")
@Entity
public class Environment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToMany (mappedBy = "environment")
    @JsonManagedReference
	    private List<Node> nodes ;
	 
	 @Column(name="minHumidity")
	  	private double minHumidity;
	 @Column(name="maxHumidity")
	    private double maxHumidity;
	 @Column(name="minTemperature")
	    private double minTemperature;
	 @Column(name="maxTemperature")
	    private double maxTemperature;
	    
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<Node> getNodes() {
			return nodes;
		}
		public void setNodes(List<Node> nodes) {
			this.nodes = nodes;
		}
		public double getMinHumidity() {
			return minHumidity;
		}
		public void setMinHumidity(double minHumidity) {
			this.minHumidity = minHumidity;
		}
		public double getMaxHumidity() {
			return maxHumidity;
		}
		public void setMaxHumidity(double maxHumidity) {
			this.maxHumidity = maxHumidity;
		}
		public double getMinTemperature() {
			return minTemperature;
		}
		public void setMinTemperature(double minTemperature) {
			this.minTemperature = minTemperature;
		}
		public double getMaxTemperature() {
			return maxTemperature;
		}
		public void setMaxTemperature(double maxTemperature) {
			this.maxTemperature = maxTemperature;
		}
	    
	    
	    


}
