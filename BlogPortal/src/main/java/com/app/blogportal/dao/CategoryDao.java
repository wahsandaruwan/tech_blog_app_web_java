package com.app.blogportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.blogportal.bean.Category;
import com.app.blogportal.bean.Post;

public class CategoryDao {
	// Properties
	private String jdbcURL = "jdbc:mysql://localhost:3306/blog_portal";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	// All db queries
	private static final String SELECT_ALL_CATS = "SELECT * FROM categories;";
	private static final String INSERT_CATEGORY = "INSERT INTO categories (cat_name) VALUES (?);";
	private static final String SELECT_CATEGORY_BY_NAME = "SELECT * FROM  categories WHERE cat_name = ? LIMIT 1;";
			
	// Default constructor
	public CategoryDao() {
				
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
	// Add category
	public void addCategory(Category cat) throws SQLException{
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT_CATEGORY)){
			preparedStatement.setString(1, cat.getCat_name());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	// Select category by category name
	public Category selectCatByName(String cat_name) {
		Category cat = null;
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CATEGORY_BY_NAME)){
			preparedStatement.setString(1, cat_name);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				cat = new Category(id, cat_name);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cat;
	}
	
	// Select all categories
	public List<Category> selectAllCats() {
		List<Category> cats = new ArrayList<>();
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_CATS);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String cat_name = rs.getString("cat_name");
				cats.add(new Category(id, cat_name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cats;
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
