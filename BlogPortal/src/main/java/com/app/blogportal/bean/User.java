package com.app.blogportal.bean;

public class User {
	// Properties
	private String username;
	private String password;
	private String type;
	
	// Parameterized constructor without id and password
	public User(String username, String type) {
		super();
		this.username = username;
		this.type = type;
	}
	
	// Parameterized constructor without id
	public User(String username, String password, String type) {
		super();
		this.username = username;
		this.password = password;
	}

	// Getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
