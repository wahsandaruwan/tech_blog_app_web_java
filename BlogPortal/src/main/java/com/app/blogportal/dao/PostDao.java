package com.app.blogportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.blogportal.bean.Post;

public class PostDao {
	// Properties
	private String jdbcURL = "jdbc:mysql://localhost:3306/blog_portal";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
		
	// All db queries
	private static final String INSERT_POST = "INSERT INTO post (title, category, creation_date, content, visibility) VALUES (?, ?, ?, ?, ?);";
	private static final String SELECT_POST_BY_ID = "SELECT * FROM post WHERE id = ?;";
	private static final String SELECT_ALL_POSTS = "SELECT * FROM post;";
	private static final String SELECT_LAST3_POST_BY_DATE = "SELECT * FROM post ORDER BY creation_date DESC LIMIT 3;";
	private static final String SELECT_LAST3_POST_BY_DATE_NORMAL = "SELECT * FROM post WHERE visibility = 'visible' ORDER BY creation_date DESC LIMIT 3;";
	private static final String SELECT_BY_SEARCHING = "SELECT * FROM post WHERE title LIKE ? LIMIT 10;";
	private static final String SELECT_BY_CATEGORY = "SELECT * FROM post WHERE category = ? ;";
	private static final String UPDATE_POST = "UPDATE post SET title = ?, category = ?, creation_date = ?, content = ?, visibility = ? WHERE id = ?;";
	private static final String DELETE_POST = "DELETE from post WHERE id = ?;";
		
	// Default constructor
	public PostDao() {
			
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
	// Add post
	public void addPost(Post post) throws SQLException{
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT_POST)){
			preparedStatement.setString(1, post.getTitle());
			preparedStatement.setString(2, post.getCategory());
			preparedStatement.setString(3, post.getCreation_date());
			preparedStatement.setString(4, post.getContent());
			preparedStatement.setString(5, post.getVisibility());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	// Update post
	public boolean updatePost(Post post) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POST);) {
			preparedStatement.setString(1, post.getTitle());
			preparedStatement.setString(2, post.getCategory());
			preparedStatement.setString(3, post.getCreation_date());
			preparedStatement.setString(4, post.getContent());
			preparedStatement.setString(5, post.getVisibility());
			preparedStatement.setInt(6, post.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// Delete post
	public boolean deletePost(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POST);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// Select a post by id
	public Post selectPost(int id) {
		Post post = null;
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_POST_BY_ID)){
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String category = rs.getString("category");
				String creation_date = rs.getString("creation_date");
				String content = rs.getString("content");
				String visibility = rs.getString("visibility");
				post = new Post(id, title, category, creation_date, content, visibility);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return post;
	}
		
	// Select all posts
	public List<Post> selectAllPosts() {
		List<Post> posts = new ArrayList<>();
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_POSTS);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String visibility = rs.getString("visibility");
				posts.add(new Post(id, title, content, visibility));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return posts;
	}
	
	// Select last 3 post by date
	public List<Post> selectLast3Posts() {
		List<Post> posts = new ArrayList<>();
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_LAST3_POST_BY_DATE);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String visibility = rs.getString("visibility");
				posts.add(new Post(id, title, content, visibility));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return posts;
	}
	
	// Select last 3 post by date for normal user
	public List<Post> selectLast3PostsNormal() {
		List<Post> posts = new ArrayList<>();
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_LAST3_POST_BY_DATE_NORMAL);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String visibility = rs.getString("visibility");
				posts.add(new Post(id, title, content, visibility));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return posts;
	}
	
	// Select posts by search query
	public List<Post> selectPostsBySearching(String query) {
		List<Post> posts = new ArrayList<>();
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BY_SEARCHING);) {
			preparedStatement.setString(1, "%"+ query + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String visibility = rs.getString("visibility");
				posts.add(new Post(id, title, content, visibility));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return posts;
	}
	
	// Select posts by category
	public List<Post> selectPostsByCategory(String category) {
		List<Post> posts = new ArrayList<>();
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BY_CATEGORY);) {
			preparedStatement.setString(1, category);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String visibility = rs.getString("visibility");
				posts.add(new Post(id, title, content, visibility));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return posts;
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
