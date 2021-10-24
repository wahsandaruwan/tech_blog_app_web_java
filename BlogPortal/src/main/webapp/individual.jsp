<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Post</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<!-- JScript -->
<script type="text/jscript">
	// Page redirecting
	if(location.href === "http://localhost:8080/BlogPortal/"){
		window.location = "http://localhost:8080/BlogPortal/home";
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
		      <li class="nav-item mr-3 ml-3">
		        <c:if test="${username == null}">
					<a href="<%=request.getContextPath()%>/login.jsp" class="btn btn-success" style="color: white !important;">Login as Admin</a>
				</c:if>
				<c:if test="${username != null}">
					<a href="<%=request.getContextPath()%>/logout" class="btn btn-success" style="color: white !important;">Logout</a>
				</c:if>
		      </li>
		    </ul>
		    <div class="nav-item mr-3 ml-3" style="color: white !important;">
		      	<c:if test="${username != null}">
					Welcome <c:out value="${username}" />
				</c:if>
		    </div>
		  </div>
		</nav>
	</div>
	
	<div class="container">
		<!-- Individual post -->
		<div class="card mt-4 mb-4">
		  <div class="card-header">
		    Date Created : <c:out value='${post.creation_date}' />
		  </div>
		  <div class="card-body">
		    <h3 class="card-title"><c:out value='${post.title}' /> <span style="font-size: 0.8rem !important;" class="bg-success text-light px-2 py-1"><c:out value="${post.visibility}" /></span></h3>
		    <div class="card-text"><c:out value='${post.content}' /></div>
		  </div>
		  <div class="card-header">
		    Category : <c:out value='${post.category}' />
		  </div>
		</div>
		
		<!-- Comments -->
		<div class="card-header">
			<h4>Comments</h4>
		</div>
		<c:forEach var="comment" items="${allCom}">
			<div class="card mb-4">
			  <div class="card-header">
			    Comment Date : <c:out value='${comment.com_date}' />
			  </div>
			  <div class="card-body">
			    <p class="card-text"><c:out value='${comment.user_name}' /> : <c:out value='${comment.com_name}' /></p>
			  </div>
			</div>
		</c:forEach>
	</div>
	
	<!-- Add comments -->
	<div class="container">
		<form action="addcom" method="post">
		  <input type="hidden" class="form-control" id="postid" name="postid" aria-describedby="emailHelp" value="<c:out value='${post.id}' />">
		  <div class="form-group">
		    <label for="username">Your User Name : </label>
		    <input type="text" class="form-control" id="username" name="username" aria-describedby="emailHelp" required="required">
		  </div>
		  <div class="form-group">
		    <label for="comment">Your Comment : </label>
		    <textarea class="form-control" id="comment" name="comment" required="required"></textarea>
		  </div>
		  <button type="submit" class="btn btn-primary">Add Comment</button>
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