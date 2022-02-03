package com.ehei.TemperaturaApplication.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Table(name="NODE")
@Entity
public class Node {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		
		@Column(name="name")
		private String name;
		
		@Column(name="position")
		private String position;
	    @OneToMany(mappedBy ="node")
	    @JsonManagedReference
	    private List<NodeData> data;
	    @ManyToOne
	    @JsonBackReference
	    private Environment environment;
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
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public List<NodeData> getData() {
			return data;
		}
		public void setData(List<NodeData> data) {
			this.data = data;
		}
		public Environment getEnvironment() {
			return environment;
		}
		public void setEnvironment(Environment environment) {
			this.environment = environment;
		}
	    
	    
	    
	    
	    

}
	