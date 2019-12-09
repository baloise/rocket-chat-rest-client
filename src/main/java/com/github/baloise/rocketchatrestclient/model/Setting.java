package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Setting {
	private String id;
	private Object value;
	
	@JsonGetter("_id")
	public String getId(){
		return id;
	}
	
	@JsonSetter("_id")
	public void setId(String id){
		this.id = id;
	}
	
	@JsonGetter("value")
	public Object getValue(){
		
		if (value instanceof String) {
			String _value = (String) value;
			if (Boolean.FALSE.toString().equalsIgnoreCase(_value)
				|| Boolean.TRUE.toString().equalsIgnoreCase(_value)) {
				return Boolean.valueOf((String) value);
			}
		}
		
		return value;
	}
	
	@JsonSetter("value")
	public void setValue(Object value){
		this.value = value;
	}
	
}
