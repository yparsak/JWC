<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>  
    <title>Title</title>
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
      <c:if test="${empty id}">
        <input id="loginbtn" class="bluebtn" name="#" type="submit" value="Login">
      </c:if>
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
 	<c:if test="${!empty admin}">
 		<input id="menubtn" class="bluebtn" name="#" type="submit" value="Add Menu Item"> <br />
 	</c:if>
	<c:if test="${!empty id}"><br/>
		<strong>${menu.name}</strong>${menu.api} <br/>
		<c:if test="${!empty menulist}">
			<br />
			<table>
			<c:forEach items="${menulist}" var="Menu">
				<tr>
					<td width="100px">
						<form method="POST" action="index">
							<input id="id" name="id" type="hidden" value="${Menu.id}"/>
							<input id="pageview" class="smlbtn"  type="submit" value="View"/>
						</form>
					</td>
					<td>${Menu.name}</td>
<c:if test="${!empty admin}">
					<td>${Menu.api}</td>
</c:if>
				</tr>
			</c:forEach>
			</table>
		</c:if>
	</c:if>
	<c:if test="${empty id}">Please login first</c:if>
  </div>
  <div class="footer">
    <div class="footer-container">
      <div class="colL">&copy; 2017</div>
      <div class="colR">1.1</div>
    </div>
  </div>
  <div id="dialog" class="dialog" title="Login">
	  <div id="loginform">
		<form method="POST" action="login">
		    <div class="row"><div class="colL">ID</div><div class="colR"><input id="id" name="id" type="text"></div></div>
		    <div class="row"><div class="colL">Password</div><div class="colR"><input id="password" name="password" type="password"></div></div>
		    <div class="row"><input id="loginsubmit" class="bluebtn" name="#" type="submit" value="Login"></div>
	    </form>
	  </div>
	  <div id="menuform">
		<form method="POST" action="index">
		    <div class="row"><div class="colL">Item</div><div class="colR"><input id="name" name="name" type="text"></div></div>
		    <div class="row"><div class="colL">API</div><div class="colR"><input id="api" name="api" type="text"></div></div>
		    <div class="row">
		    	<input id="parent" name="parent" type="hidden" value="${parent}">
		    	<input id="menusubmit" class="bluebtn" name="#" type="submit" value="Add">
		    </div>
		    <div class="row"><i>Keep API blank for sub menu items</i></div>
	    </form>
	  </div>
  </div>
  <div id="coverup" class="coverup"></div>
  </body>
</html>