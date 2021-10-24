package com.app.blogportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.blogportal.bean.Blog;
import com.app.blogportal.bean.Post;

public class BlogDao {
	// Properties
	private String jdbcURL = "jdbc:mysql://localhost:3306/blog_portal";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
			
	// All db queries
	private static final String SELECT_CONTACT_ABOUT_INFO = "SELECT * FROM contact_about_details;";
	private static final String UPDATE_CON_ABO = "UPDATE contact_about_details SET address = ?, contact_num1 = ?, contact_num2 = ?, contact_email1 = ?, contact_email2 = ?, about_info = ? WHERE id = ?;";
					
	// Default constructor
	public BlogDao() {
						
	}
		
	// Create db connection
	protected Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// DB operation methods
	// Update contact and about information
	public boolean updateConAbo(Blog blog) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CON_ABO);) {
			preparedStatement.setString(1, blog.getAddress());
			preparedStatement.setInt(2, blog.getCon_num1());
			preparedStatement.setInt(3, blog.getCon_num2());
			preparedStatement.setString(4, blog.getCon_email1());
			preparedStatement.setString(5, blog.getCon_email2());
			preparedStatement.setString(6, blog.getAbout_info());
			preparedStatement.setInt(7, blog.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
		
	// Select about and contact info
	public Blog selectAboutContact() {
		Blog blog = null;
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CONTACT_ABOUT_INFO)){
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String address = rs.getString("address");
				int con_num1 = rs.getInt("contact_num1");
				int con_num2 = rs.getInt("contact_num2");
				String con_email1 = rs.getString("contact_email1");
				String con_email2 = rs.getString("contact_email2");
				String about_info = rs.getString("about_info");
				blog = new Blog(id, address, con_num1, con_num2, con_email1, con_email2, about_info);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return blog;
	}
	
	// Method to print errors
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState : " + ((SQLException) e).getSQLState());
				System.err.println("Error Code : " + ((SQLException) e).getErrorCode());
				System.err.println("Message : " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.err.println("Cause : " + t);
					t = t.getCause();
				}
			}
		}
	}
}
