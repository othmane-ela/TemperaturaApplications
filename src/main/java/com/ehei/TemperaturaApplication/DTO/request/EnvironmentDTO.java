package com.ehei.TemperaturaApplication.DTO.request;


public class EnvironmentDTO {
	

	private String name;
	
	private String description;
	
	  	private double minHumidity;

	    private double maxHumidity;

	    private double minTemperature;
	
	    private double maxTemperature;

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
