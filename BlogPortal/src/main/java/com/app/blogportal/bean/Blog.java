package com.app.blogportal.bean;

public class Blog {
	// properties
	private int id;
	private String address;
	private int con_num1;
	private int con_num2;
	private String con_email1;
	private String con_email2;
	private String about_info;
	
	// Parameterized constructor
	public Blog(int id, String address, int con_num1, int con_num2, String con_email1, String con_email2,
			String about_info) {
		super();
		this.id = id;
		this.address = address;
		this.con_num1 = con_num1;
		this.con_num2 = con_num2;
		this.con_email1 = con_email1;
		this.con_email2 = con_email2;
		this.about_info = about_info;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCon_num1() {
		return con_num1;
	}

	public void setCon_num1(int con_num1) {
		this.con_num1 = con_num1;
	}

	public int getCon_num2() {
		return con_num2;
	}

	public void setCon_num2(int con_num2) {
		this.con_num2 = con_num2;
	}

	public String getCon_email1() {
		return con_email1;
	}

	public void setCon_email1(String con_email1) {
		this.con_email1 = con_email1;
	}

	public String getCon_email2() {
		return con_email2;
	}

	public void setCon_email2(String con_email2) {
		this.con_email2 = con_email2;
	}

	public String getAbout_info() {
		return about_info;
	}

	public void setAbout_info(String about_info) {
		this.about_info = about_info;
	}
	
}
