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
<title>Edit Contact & About Information</title>
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
	function conAboVal(){
		var address = document.conaboform.address.value;
		var con_num1 = document.conaboform.con_num1.value;
		var con_num2 = document.conaboform.con_num2.value;
		var con_email1 = document.conaboform.con_email1.value;
		var con_email2 = document.conaboform.con_email2.value;
		var about_info = document.conaboform.about_info.value;
		if(address == ""){
			alert("Please enter the Address!");
			return false;
		}
		else if (con_num1 == ""){
			alert("Please enter a Contact Number 01!");
			return false;
		}
		else if (con_num2 == ""){
			alert("Please enter a Contact Number 02!");
			return false;
		}
		else if (con_email1 == ""){
			alert("Please enter a Contact Email 01!");
			return false;
		}
		else if (con_email2 == ""){
			alert("Please enter a Contact Email 02!");
			return false;
		}
		else if (about_info == ""){
			alert("Please enter About Information!");
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
	
	<!-- Contact and About information editing form -->
	<div class="container mt-5 mb-5 d-flex justify-content-center">
		<form action="updateconabo" onSubmit="return conAboVal()" id="conaboform" name="conaboform" method="post" class="w-75">
		  <h3>Edit Contact and About Information</h3>
		  <c:if test="${aboutContat != null}">
			<input type="hidden" name="id" value="<c:out value='${aboutContat.id}' />" />
		  </c:if>
		  <div class="form-group">
		    <label for="address">Address : </label> 
		    <textarea class="form-control" id="address" name="address" placeholder="Enter Address"><c:out value='${aboutContat.address}' /></textarea>
		  </div>
		  <div class="form-group">
		    <label for="con_num1">Contact Number 01 : </label> 
		    <input type="number" value="<c:out value='${aboutContat.con_num1}' />" class="form-control" id="con_num1" name="con_num1" placeholder="Enter Contact Number 01">
		  </div>
		  <div class="form-group">
		    <label for="con_num2">Contact Number 02 : </label> 
		    <input type="number" value="<c:out value='${aboutContat.con_num2}' />" class="form-control" id="con_num2" name="con_num2" placeholder="Enter Contact Number 02">
		  </div>
		  <div class="form-group">
		    <label for="con_email1">Contact Email 01 : </label> 
		    <input type="email" value="<c:out value='${aboutContat.con_email1}' />" class="form-control" id="con_email1" name="con_email1" placeholder="Enter Contact Email 01">
		  </div>
		  <div class="form-group">
		    <label for="con_email2">Contact Email 02 : </label> 
		    <input type="email" value="<c:out value='${aboutContat.con_email2}' />" class="form-control" id="con_email2" name="con_email2" placeholder="Enter Contact Email 02">
		  </div>
		  <div class="form-group">
		    <label for="about_info">About Information : </label> 
		    <textarea class="form-control" id="about_info" name="about_info" placeholder="Enter About Information"><c:out value='${aboutContat.about_info}' /></textarea>
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