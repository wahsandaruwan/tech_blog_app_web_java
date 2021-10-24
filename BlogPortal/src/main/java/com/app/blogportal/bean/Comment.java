package com.app.blogportal.bean;

public class Comment {
	// properties
	private int id;
	private int post_id;
	private String user_name;
	private String com_name;
	private String com_date;
	
	// Parameterized constructor without id, and com_date
	public Comment(int post_id, String user_name, String com_name) {
		super();
		this.post_id = post_id;
		this.user_name = user_name;
		this.com_name = com_name;
	}
	
	// Parameterized constructor
	public Comment(int id, int post_id, String user_name, String com_name, String com_date) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.user_name = user_name;
		this.com_name = com_name;
		this.com_date = com_date;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_date() {
		return com_date;
	}

	public void setCom_date(String com_date) {
		this.com_date = com_date;
	}
}
