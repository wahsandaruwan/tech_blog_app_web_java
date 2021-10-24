package com.app.blogportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.blogportal.bean.Post;
import com.app.blogportal.bean.User;

public class UserDao {
	// Properties
	private String jdbcURL = "jdbc:mysql://localhost:3306/blog_portal";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
			
	// All db queries
	private static final String SELECT_USER_BY_CREDEBTIALS = "SELECT * FROM users WHERE username = ? AND password = ?;";
			
	// Default constructor
	public UserDao() {
				
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
	// Select user by username and password
	public User selectUser(String username, String password) {
		User user = null;
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_BY_CREDEBTIALS)){
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String type = rs.getString("type");
				user = new User(username, type);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
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
