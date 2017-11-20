<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Form Handling</title>
</head>
<body>
	<!-- <h2>Your user info</h2>
	<h6>
		<a href="j_spring_security_logout">Click here to logout</a>
	</h6> -->
	<sec:authorize access="isAuthenticated()">
 Username: <sec:authentication property="principal.username" />
 Role: <sec:authentication property="principal.authorities" />
	</sec:authorize>
	<h4>
	<a href="${pageContext.request.contextPath}/menu">Back to Menu</a></h4>
	<h2>Add Employee Data</h2>
	<form:form method="POST" action="${pageContext.request.contextPath}/employee/save">
		<table>
			<tr>
				<td><form:label path="id">Employee ID:</form:label></td>
				<td><form:input path="id" value="${employee.id}"
						readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Employee Name:</form:label></td>
				<td><form:input path="name" value="${employee.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="age">Employee Age:</form:label></td>
				<td><form:input path="age" value="${employee.age}" /></td>
			</tr>
			<tr>
				<td><form:label path="salary">Employee Salary:</form:label></td>
				<td><form:input path="salary" value="${employee.salary}" /></td>
			</tr>

			<tr>
				<td><form:label path="address">Employee Address:</form:label></td>
				<td><form:input path="address" value="${employee.address}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="<c:url value="/j_spring_security_logout" />"> Logout</a>
		</h2>
	</c:if>

</body>
</html>