package com.github.baloise.rocketchatrestclient;

public class Configuration {
	
	private final String serverUrl;
	private final String user;
	private final String password;
	private final String apiVersionPrefix;
	private  String xAuthToken;
	private  String xUserId;
	
	public Configuration(String serverUrl, String apiVersionPrefix, String user, String password) {
		super();
		this.apiVersionPrefix = apiVersionPrefix;
		this.serverUrl = serverUrl;
		this.user = user;
		this.password = password;
		
	}
	
	public String getxAuthToken() {
		return xAuthToken;
	}

	public void setxAuthToken(String xAuthToken) {
		this.xAuthToken = xAuthToken;
	}

	public String getxUserId() {
		return xUserId;
	}

	public void setxUserId(String xUserId) {
		this.xUserId = xUserId;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getApiVersionPrefix() {
		return apiVersionPrefix;
	}

	
	
	
	
	

}
