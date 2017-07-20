<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>  
    <title>Users</title>
    <meta name="description" content="#">
    <meta name="keywords" content="#">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/custom.css">
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/custom.js"></script>  
  </head>
  <body>
  <div class="header">
    <div class="header-container">
      <div class="logo"></div>
      <div class="loginbar">
      <c:if test="${! empty id}">
      	
        <a href="logout">Logout</a>
      </c:if>
      </div>
      <div class="menubar"> <a href="index">Home</a>
		<c:if test="${!empty admin}">| <a href="users">Users</a> |<a href="departments">Departments</a></c:if>
      </div>
    </div>
  </div>
  <div class="main">
    <input id="userbtn" class="bluebtn" name="#" type="submit" value="Add User"> <br />
	<c:if test="${!empty userslist}">
		<table>
		<tr>
			<td width="100"><strong>User ID</strong></td>
			<td width="200"><strong>First Name</strong></td>
			<td width="200"><strong>Last Name</strong></td>
			<td width="200"><strong>Email</strong></td>
			<td width="100"> <!--  --></td>
		</tr>
		<c:forEach items="${userslist}" var="user">
			<tr>
				<td width="100">${user.id}</td>
				<td width="200">${user.name}</td>
				<td width="200">${user.lastname}</td>
				<td width="200">${user.email}</td>
				<td width="100"><a href="<c:url value='/user${user.id}' />" >View</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
  </div>
  <div class="footer">
    <div class="footer-container">
      <div class="colL">&copy; 2017</div>
      <div class="colR">1.1</div>
    </div>
  </div>
  <div id="dialog" class="dialog" title="Users">
		<form method="POST" action="add_user">
      		<div class="row"><div class="colL">Name</div><div class="colR"><input id="name" name="name" type="text"></div></div>
      		<div class="row"><div class="colL">Last Name</div><div class="colR"><input id="lastname" name="lastname" type="text"></div></div>
      		<div class="row"><div class="colL">Email</div><div class="colR"><input id="email" name="email" type="text"></div></div>
      		<div class="row"><div class="colL">Password</div><div class="colR"><input id="password" name="password" type="password"></div></div>
      		<div class="row"><input id="adduser" class="bluebtn" name="#" type="submit" value="Add"></div>
    	</form>
  </div>
  <div id="coverup" class="coverup"></div>
  </body>
</html>