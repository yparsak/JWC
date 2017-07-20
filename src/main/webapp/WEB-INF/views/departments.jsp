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
  <div class="main">
	<input id="deptbtn" class="bluebtn" name="#" type="submit" value="Add Department"> <br />
	<c:if test="${!empty deptlist}">
		<table>
		<tr>
			<td><strong>Name</strong></td>
			<td><!--  --></td>
		</tr>
		<c:forEach items="${deptlist}" var="UsrDptRole">
			<tr>
				<td>${UsrDptRole.deptname}</td>
				<td>
			<form method="POST" action="department">
				<input id="deptname" name="deptname" type="hidden" value="${UsrDptRole.deptname}"/>
				<input id="viewDept" class="smlbtn"  type="submit" value="View"/>
			</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<br />
	<input id="rolebtn" class="bluebtn" name="#" type="submit" value="Add Role"> <br />
	<c:if test="${!empty rolelist}">
		<table>
		<tr>
			<td><strong>Name</strong></td>
		</tr>
		<c:forEach items="${rolelist}" var="UsrDptRole">
			<tr>
				<td>${UsrDptRole.rolename}</td>
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
  <div id="dialog" class="dialog" title="">
  	<div id="newdptform">
		<form method="POST" action="add_department">
      		<div class="row"><div class="colL">Name</div><div class="colR"><input id="deptname" name="deptname" type="text"></div></div>
      		<div class="row"><div class="row"><input id="add_department" class="bluebtn" name="#" type="submit" value="Add"></div></div>
    	</form>
    </div>
    <div id="newroleform">
		<form method="POST" action="add_role">
      		<div class="row"><div class="colL">Name</div><div class="colR"><input id="rolename" name="rolename" type="text"></div></div>
      		<div class="row"><div class="row"><input id="add_department" class="bluebtn" name="#" type="submit" value="Add"></div></div>
    	</form>
    </div>
  </div>
  <div id="coverup" class="coverup"></div>
  </body>
</html>