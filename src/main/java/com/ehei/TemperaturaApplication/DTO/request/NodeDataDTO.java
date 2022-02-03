package com.ehei.TemperaturaApplication.DTO.request;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class NodeDataDTO {
	
    	private Long node;
	    private double temperature;
	    private double humidity;
	    private LocalDateTime dateTime;
	


	    
	    public NodeDataDTO(){
	        this.dateTime= LocalDateTime.now(ZoneId.of("GMT+1"));
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


		public Long getNode() {
			return node;
		}


		public void setNode(Long node) {
			this.node = node;
		}


	
	    
	    
	    
}
