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
<title>Add or Edit Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src='https://cdn.tiny.cloud/1/sjtbmdp0sx2soe9754whmig5xg5rhl2hnbwh61qcspx0lah8/tinymce/5/tinymce.min.js' referrerpolicy="origin">
</script>
<script>
   //tinymce.init({
     //selector: '#content'
   //});
</script>

<!-- JScript -->
<script type="text/jscript">
	//Add & edit form validation
	function postVal(){
		var title = document.postform.title.value;
		var creation_date = document.postform.creation_date.value;
		var content = document.postform.content.value;
		if(title == ""){
			alert("Please enter the Title!");
			return false;
		}
		else if (creation_date == ""){
			alert("Please enter a Creation Date!");
			return false;
		}
		else if (content == ""){
			alert("Please enter a Content!");
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
	
	<!-- Post add & edit form -->
	<div class="container mt-5 mb-5 d-flex justify-content-center">
		<c:if test="${post == null}">
			<form action="addpost" id="postform" onSubmit="return postVal()" name="postform" method="post" class="w-75">
			<h3>Add New Post</h3>
		</c:if>
		<c:if test="${post != null}">
			<form action="updatepost" id="postform" onSubmit="return postVal()" name="postform" method="post" class="w-75">
			<h3>Edit Post</h3>
		</c:if>
		  <c:if test="${post != null}">
			<input type="hidden" name="id" value="<c:out value='${post.id}' />" />
		  </c:if>
		  <div class="form-group">
		    <label for="title">Title : </label> 
		    <input type="text" value="<c:out value='${post.title}' />" class="form-control" id="title" name="title" placeholder="Enter Title">
		  </div>
		  <div class="form-group">
		    <label for="category">Category : <c:out value='${post.category}' /></label> 
		    <select id="category" name="category">
		    	<c:forEach var="cat" items="${allCats}">
		    		<option value='${cat.cat_name}'><c:out value='${cat.cat_name}' /></option>
				</c:forEach>
		    </select>
		  </div>
		  <div class="form-group">
		    <label for="creation_date">Creation Date : </label> 
		    <input type="date" value="<c:out value='${post.creation_date}' />" class="form-control" id="creation_date" name="creation_date" placeholder="Enter Date">
		  </div>
		  <div class="form-group">
		    <label for="content">Content : </label> 
		    <textarea class="form-control" id="content" name="content" placeholder="Enter Content"><c:out value='${post.content}' /></textarea>
		  </div>
		  <div class="form-group">
		    <label for="visibility">Visibility : <c:out value='${post.visibility}' /></label> 
		    <select id="visibility" name="visibility">
		    	<option value='visible'>visible</option>
		    	<option value='hidden'>hidden</option>
		    </select>
		  </div>
		  <button type="submit" class="btn btn-primary w-100">Save</button>
		</form>
		<div id="out"></div>
	</div>
	
	<!-- Footer -->
	<footer class="page-footer font-small bg-primary mt-5 container">
	  <div class="footer-copyright text-center text-light py-3">© 2021 Copyright: Tech Blog | 
	    <a class="text-light" href="#">Kamoj Alwis</a>
	  </div>
	</footer>
</body>
</html>