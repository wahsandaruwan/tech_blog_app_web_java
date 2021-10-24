package com.app.blogportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.blogportal.bean.Category;
import com.app.blogportal.bean.Comment;

public class CommentDao {
	// Properties
	private String jdbcURL = "jdbc:mysql://localhost:3306/blog_portal";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
		
	// All db queries
	private static final String SELECT_COMMENTS_BY_POST_ID = "SELECT * FROM comments WHERE post_id = ?;";
	private static final String INSERT_NEW_COMMENT = "INSERT INTO comments (post_id, user_name, com_name, com_date) VALUES (?, ?, ?, ?);";
				
	// Default constructor
	public CommentDao() {
					
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
	// Insert comment
	public void insertComment(Comment comment) throws SQLException{
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT_NEW_COMMENT)){
			preparedStatement.setInt(1, comment.getPost_id());
			preparedStatement.setString(2, comment.getUser_name());
			preparedStatement.setString(3, comment.getCom_name());
			preparedStatement.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
		
	// Select all comments by post id
	public List<Comment> selectAllComment(int post_id) {
		List<Comment> comment = new ArrayList<>();
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COMMENTS_BY_POST_ID);) {
			preparedStatement.setInt(1, post_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String user_name = rs.getString("user_name");
				String com_name = rs.getString("com_name");
				String com_date = rs.getString("com_date");
				comment.add(new Comment(id, post_id, user_name, com_name, com_date));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return comment;
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
