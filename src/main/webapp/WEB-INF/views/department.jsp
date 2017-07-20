<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>  
    <title>User ${id}</title>
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
  <div class="main">${department}<br />
  		<table>
		<tr>
			<td><strong>Name</strong></td>
			<td><strong>Last Name</strong></td>			
			<td><strong>Role</strong></td>
		</tr>
		<c:forEach items="${deptrolelist}" var="UsrDptRole">
		<tr>
			<td><a href="user${UsrDptRole.userid}">${UsrDptRole.username}</a></td>
			<td><a href="user${UsrDptRole.userid}">${UsrDptRole.userlastname}</a></td>
			<td>${UsrDptRole.rolename}</td>
		</tr>
		</c:forEach>
	</table>  
  </div>
  <div class="footer">
    <div class="footer-container">
      <div class="colL">&copy; 2017</div>
      <div class="colR">1.1</div>
    </div>
  </div>
  <div id="dialog" class="dialog" title="Add Department">
		<form method="POST" action="add_department">
		    <input id="name" name="name" type="text">
      		<div class="row"><input id="add_department" class="bluebtn" name="#" type="submit" value="Add Department"></div>
    	</form>
  </div>
  <div id="coverup" class="coverup"></div>
  </body>
</html>