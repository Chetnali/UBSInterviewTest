package com.ubs.opsit.interviews;

public enum LightOnOffEnum {

	
Red("R"),Yellow("Y"),Off("O");
	
	
	private String value;  
	private LightOnOffEnum(String value){ 
		
	this.value=value;  
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}  
	
}
