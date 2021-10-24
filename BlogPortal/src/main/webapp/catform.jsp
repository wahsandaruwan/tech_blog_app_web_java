<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	if(session.getAttribute("username") == null){
		response.sendRedirect("home.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Category Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<!-- JScript -->
<script type="text/jscript">
	// Page redirecting
	if(location.href === "http://localhost:8080/BlogPortal/"){
		window.location = "http://localhost:8080/BlogPortal/home";
	}
	
	//Login form validation
	function catFormVal(){
		var cat_name = document.catform.cat_name.value;
		if(cat_name == ""){
			alert("Please enter a Category Name!");
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
	
	<!-- Add category form -->
	<div class="container mt-5 mb-5 d-flex justify-content-center">
		<form action="addcat" onsubmit="return catFormVal()" id="catform" name="catform" method="post" class="col-sm-4">
		  <h3>Add New Category</h3>
		  <div class="form-group">
		    <label for="username">Category Name : </label> 
		    <input type="text" class="form-control" id="cat_name" name="cat_name" placeholder="Enter Category Name">
		  </div>
		  <button type="submit" class="btn btn-primary w-100">Add Category</button>
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