package com.app.blogportal.bean;

public class Category {
	// properties
	private int id;
	private String cat_name;
	
	// Parameterized constructor without id
	public Category(String cat_name) {
		super();
		this.cat_name = cat_name;
	}
	
	// Parameterized constructor
	public Category(int id, String cat_name) {
		super();
		this.id = id;
		this.cat_name = cat_name;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
}
