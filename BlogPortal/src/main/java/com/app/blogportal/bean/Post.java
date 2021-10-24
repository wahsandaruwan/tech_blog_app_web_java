package com.app.blogportal.bean;

public class Post {
	// properties
	private int id;
	private String title;
	private String category;
	private String creation_date;
	private String content;
	private String visibility;
	
	// Parameterized constructor without id
	public Post(String title, String category, String creation_date, String content, String visibility) {
		super();
		this.title = title;
		this.category = category;
		this.creation_date = creation_date;
		this.content = content;
		this.visibility = visibility;
	}
	
	// Parameterized constructor without category, creation_date
	public Post(int id, String title, String content, String visibility) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.visibility = visibility;
	}
	
	// Parameterized constructor
	public Post(int id, String title, String category, String creation_date, String content, String visibility) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.creation_date = creation_date;
		this.content = content;
		this.visibility = visibility;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
}
