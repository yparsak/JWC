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
    <c:url var="addAction" value="update_user" ></c:url>
    <form:form action="${addAction}" commandName="User">
		<table>
		    <tr><th colspan="2">User Info</th></tr>
			<tr><td><form:label path="id"><spring:message text="ID"/></form:label></td>
				<td><form:input path="id" readonly="true" size="8"  disabled="true" />
					<form:hidden path="id" />
				</td>
			</tr>
			<tr><td><form:label path="name"><spring:message text="Name"/></form:label></td>
			    <td><form:input path="name" /></td>
			</tr>
			<tr><td><form:label path="lastname"><spring:message text="Last Name"/></form:label></td>
			    <td><form:input path="lastname" /></td>
			</tr>
			<tr><td><form:label path="email"><spring:message text="Email"/></form:label></td>
			    <td><form:input path="email" /></td>
			</tr>
			<tr><td><form:label path="password"><spring:message text="Password"/></form:label></td>
			    <td><form:input path="password" class="password"/></td>
			</tr>
			<tr><td colspan="2" align="right"><input id="updateuser" class="bluebtn" name="#" type="submit" value="Update"></td></tr>
		</table>
	</form:form>
	<br />
    <input id="addrolebtn" class="bluebtn" name="#" type="submit" value="Add Role"> <br />
    <br />
	<c:if test="${!empty deptrolelist}">
    <table>
    	<tr><th>Department</th><th>Role</th><th></th></tr> 
		<c:forEach items="${deptrolelist}" var="UsrDptRole"> 
			<tr>
				<td>
					<form method="POST" action="department">
					<input id="deptname" name="deptname" type="hidden" value="${UsrDptRole.deptname}"/>
					<input id="viewDept" class="smlbtn"  type="submit" value="${UsrDptRole.deptname}"/>
					</form>
				</td>
				<td>${UsrDptRole.deptname}</td><td>${UsrDptRole.rolename}</td>
				<td>
					<form method="POST" action="remove_userrole">
					<input id="id" name="id" type="hidden" value="${UsrDptRole.id}"/>
					<input id="userid" name="userid" type="hidden" value="${UsrDptRole.userid}"/>
					<input id="viewDept" class="smlbtn"  type="submit" value="Remove"/>
					</form>
				</td>
			</tr> 
		</c:forEach>
	</table>
	</c:if>
	<br /><br />
	<a href="removeuser${userid}">Remove User</a>
  </div>
  <div class="footer">
    <div class="footer-container">
      <div class="colL">&copy; 2017</div>
      <div class="colR">1.1</div>
    </div>
  </div>
  <div id="dialog" class="dialog" title="Add Role">
		<form method="POST" action="add_user_role">
			<table>
				<tr><th>Department</th>
					<td>
						<select id='deptname' name='deptname'>
							<c:forEach items="${deptlist}" var="UsrDptRole">
								<option>${UsrDptRole.deptname}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr><th>Role</th>
					<td>
						<select id='rolename' name='rolename'>
							<c:forEach items="${rolelist}" var="UsrDptRole">
								<option>${UsrDptRole.rolename}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		    <input id="userid" name="userid" type="hidden" value="${userid}">
      		<div class="row"><input id="adduseruser" class="bluebtn" name="#" type="submit" value="Add Role"></div>
    	</form>
  </div>
  <div id="coverup" class="coverup"></div>
  </body>
</html>