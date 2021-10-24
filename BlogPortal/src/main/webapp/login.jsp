<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	if(session.getAttribute("username") != null){
		response.sendRedirect("home.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<!-- JScript -->
<script type="text/jscript">
	// Page redirecting
	if(location.href === "http://localhost:8080/BlogPortal/"){
		window.location = "http://localhost:8080/BlogPortal/home";
	}
	
	//Login form validation
	function loginVal(){
		var username = document.lgform.username.value;
		var password = document.lgform.password.value;
		if(username == ""){
			alert("Please enter the Username!");
			return false;
		}
		else if (password == ""){
			alert("Please enter the Password!");
			return false;
		}
	}
</script>
</head>
<body>
	<!-- Jumbotron -->
	<div class="container">
		<div class="jumbotron jumbotron-fluid pl-4 pr-4" style="margin-bottom: 0 !important;">
		  <div class="container">
		    <h1 class="display-4">Welcome to Tech Blog</h1>
		    <p class="lead">Read this blog to know very useful information about Information Technology!</p>
		  </div>
		</div>
	</div>
	
	<!-- Navbar -->
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item mr-3 ml-3">
		        <a href="<%=request.getContextPath()%>/home" class="nav-link" style="color: white !important;">Home</a>
		      </li>
		      <li class="nav-item mr-3 ml-3">
		        <a href="<%=request.getContextPath()%>/posts" class="nav-link" style="color: white !important;">Posts</a>
		      </li>
		      <li class="nav-item mr-3 ml-3">
		        <a href="<%=request.getContextPath()%>/about" class="nav-link" style="color: white !important;">About Us</a>
		      </li>
		    </ul>
		  </div>
		</nav>
	</div>
	
	<!-- Login form -->
	<div class="container mt-5 mb-5 d-flex justify-content-center">
		<form action="loggedin" onsubmit="return loginVal()" id="lgform" name="lgform" method="post" class="col-sm-4">
		  <h3>Login as Admin</h3>
		  <div class="form-group">
		    <label for="username">Username</label> 
		    <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username">
		  </div>
		  <div class="form-group">
		    <label for=""password"">Password</label>
		    <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password">
		  </div>
		  <button type="submit" class="btn btn-primary w-100">Login</button>
		</form>
	</div>
	
	<!-- Footer -->
	<footer class="page-footer font-small bg-primary mt-5 container">
	  <div class="footer-copyright text-center text-light py-3">© 2021 Copyright: Tech Blog | 
	    <a class="text-light" href="#">Kamoj Alwis</a>
	  </div>
	</footer>
</body>
</html>