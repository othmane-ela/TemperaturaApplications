package com.ehei.TemperaturaApplication.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name="NODE_DATA")
@Entity
public class NodeData {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		@Column(name="temperature")
	    private double temperature;
		@Column(name="humidity")
	    private double humidity;
		@Column(name="date_time")
	    private LocalDateTime dateTime;
	    
	    @ManyToOne
	    @JsonBackReference
	    private Node node;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public double getTemperature() {
			return temperature;
		}
		public void setTemperature(double temperature) {
			this.temperature = temperature;
		}
		public double getHumidity() {
			return humidity;
		}
		public void setHumidity(double humidity) {
			this.humidity = humidity;
		}
		public LocalDateTime getDateTime() {
			return dateTime;
		}
		public void setDateTime(LocalDateTime dateTime) {
			this.dateTime = dateTime;
		}
		public Node getNode() {
			return node;
		}
		public void setNode(Node node) {
			this.node = node;
		}
		@Override
		public String toString() {
			return "NodeData [id=" + id + ", temperature=" + temperature + ", humidity=" + humidity + ", dateTime="
					+ dateTime + ", node=" + node + "]";
		}
		
		
		
	    
	    

}
