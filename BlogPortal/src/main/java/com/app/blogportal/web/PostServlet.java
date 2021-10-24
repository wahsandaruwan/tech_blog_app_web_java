package com.app.blogportal.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.blogportal.bean.Blog;
import com.app.blogportal.bean.Category;
import com.app.blogportal.bean.Comment;
import com.app.blogportal.bean.Post;
import com.app.blogportal.bean.User;
import com.app.blogportal.dao.BlogDao;
import com.app.blogportal.dao.CategoryDao;
import com.app.blogportal.dao.CommentDao;
import com.app.blogportal.dao.PostDao;
import com.app.blogportal.dao.UserDao;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// Note dao instance
	private PostDao postDao =  new PostDao();
	
	// Category dao instance
	private CategoryDao catDao = new CategoryDao();
	
	// Comment dao instance
	private CommentDao comDao = new CommentDao();
	
	// Blog dao instance
	private BlogDao blogDao = new BlogDao();
	
	// User dao instance
	private UserDao userDao = new UserDao();
	
	// Search state
	public boolean isSearch = false;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handle endpoints
		String action = request.getServletPath();
		switch(action) {
			case "/individual":
				try {
					showIndividualPost(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/posts":
				isSearch = false;
				try {
					showAllPosts(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/about":
				try {
					showAboutContact(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/addcom":
				try {
					addComment(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/loginform":
				try {
					showLoginForm(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/search":
				isSearch = true;
				System.out.println("Add Page!");
				try {
					searchStart(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/cat_post":
				try {
					showPostsByCategory(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/loggedin":
				try {
					loginUser(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/logout":
				try {
					logoutUser(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/newform":
				try {
					showNewForm(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/showcatform":
				try {
					showAddCatForm(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/editpost":
				try {
					showEditForm(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/editconabo":
				try {
					showConAboForm(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/addpost":
				try {
					addNewPost(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/updatepost":
				try {
					updatePost(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/deletepost":
				try {
					deletePost(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/addcat":
				try {
					addCategory(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/updateconabo":
				try {
					updateConAbo(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				try {
					showHomeData(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
		
	}
	
	// Add new post
	private void addNewPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String creation_date = request.getParameter("creation_date");
		String content = request.getParameter("content");
		String visibility = request.getParameter("visibility");
		Post newPost = new Post(title, category, creation_date, content, visibility);
		postDao.addPost(newPost);
		response.sendRedirect("posts");
	}
	
	// Update post
	private void updatePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int post_id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String creation_date = request.getParameter("creation_date");
		String content = request.getParameter("content");
		String visibility = request.getParameter("visibility");
		Post post = new Post(post_id, title, category, creation_date, content, visibility);
		postDao.updatePost(post);
		response.sendRedirect("posts");
	}
	
	// Delete post
	private void deletePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int post_id = Integer.parseInt(request.getParameter("id"));
		postDao.deletePost(post_id);
		response.sendRedirect("posts");
	}
	
	// New form to add posts
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Category> allCats = catDao.selectAllCats();
		request.setAttribute("allCats", allCats);
		RequestDispatcher dispatcher = request.getRequestDispatcher("postform.jsp");
		dispatcher.forward(request, response);
	}
	
	// Form to edit posts
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int post_id = Integer.parseInt(request.getParameter("id"));
		List<Category> allCats = catDao.selectAllCats();
		Post existingPost = postDao.selectPost(post_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("postform.jsp");
		request.setAttribute("post", existingPost);
		request.setAttribute("allCats", allCats);
		dispatcher.forward(request, response);
	}
	
	// Show home page data
	private void showHomeData(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Post> list3Posts = null;
		// Select a function according to the user
		HttpSession login = request.getSession();
		if(login.getAttribute("username") != null) {
			list3Posts = postDao.selectLast3Posts();
		}
		else {
			list3Posts = postDao.selectLast3PostsNormal();
		}
		 
		List<Category> allCats = catDao.selectAllCats();
		request.setAttribute("list3Posts", list3Posts);
		request.setAttribute("allCats", allCats);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	
	// Update contact and about information
	private void updateConAbo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int conabo_id = Integer.parseInt(request.getParameter("id"));
		String address = request.getParameter("address");
		int con_num1 = Integer.parseInt(request.getParameter("con_num1"));
		int con_num2 = Integer.parseInt(request.getParameter("con_num2"));
		String con_email1 = request.getParameter("con_email1");
		String con_email2 = request.getParameter("con_email2");
		String about_info = request.getParameter("about_info");
		Blog blog = new Blog(conabo_id, address, con_num1, con_num2, con_email1, con_email2, about_info);
		blogDao.updateConAbo(blog);
		response.sendRedirect("about");
	}
	
	// Show about and contact information
	private void showAboutContact(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		Blog aboutContat = blogDao.selectAboutContact();
		request.setAttribute("aboutContat", aboutContat);
		RequestDispatcher dispatcher = request.getRequestDispatcher("about.jsp");
		dispatcher.forward(request, response);
	}
	
	// Form to edit contact and about information
	private void showConAboForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Blog aboutContat = blogDao.selectAboutContact();
		request.setAttribute("aboutContat", aboutContat);
		RequestDispatcher dispatcher = request.getRequestDispatcher("conaboform.jsp");
		dispatcher.forward(request, response);
	}
	
	// Show all posts
	private void showAllPosts(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Post> listPosts = postDao.selectAllPosts();
		request.setAttribute("listPosts", listPosts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		dispatcher.forward(request, response);
	}
	
	// Show individual post
	private void showIndividualPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int post_id = Integer.parseInt(request.getParameter("id"));
		Post clickedPost = postDao.selectPost(post_id);
		List<Comment> allCom = comDao.selectAllComment(post_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("individual.jsp");
		request.setAttribute("post", clickedPost);
		request.setAttribute("allCom", allCom);
		dispatcher.forward(request, response);
	}
	
	// New add category form
	private void showAddCatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("catform.jsp");
		dispatcher.forward(request, response);
	}
	
	// Add comments
	private void addComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int postid = Integer.parseInt(request.getParameter("postid"));
		String username = request.getParameter("username");
		String comment = request.getParameter("comment");
		Comment newCom = new Comment(postid, username, comment);
		comDao.insertComment(newCom);
		response.sendRedirect("individual?id="+postid);
	}
	
	// Add new post
	@SuppressWarnings("unused")
	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String cat_name = request.getParameter("cat_name");
		Category exsisting_cat = catDao.selectCatByName(cat_name);
		if(exsisting_cat == null) {
			Category newCat = new Category(cat_name);
			catDao.addCategory(newCat);
			response.sendRedirect("home.jsp");
		}
		else {
			response.sendRedirect("showcatform");
		}
	}
	
	// Search posts
	private void searchStart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String query = request.getParameter("search");
		List<Post> searchPosts = postDao.selectPostsBySearching(query);
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		request.setAttribute("listPosts", searchPosts);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Show post by category
	private void showPostsByCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String category = request.getParameter("cat");
		List<Post> postCat = postDao.selectPostsByCategory(category);
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		request.setAttribute("listPosts", postCat);
		dispatcher.forward(request, response);
	}
	
	// Show login form
	private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	
	// User login
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectUser(username, password);
		if(user != null) {
			HttpSession login = request.getSession(true);
			login.setAttribute("username", user.getUsername());
			login.setAttribute("type", user.getType());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// User logout
	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession logout = request.getSession();
		logout.removeAttribute("username");
		response.sendRedirect("home.jsp");
	}
}
