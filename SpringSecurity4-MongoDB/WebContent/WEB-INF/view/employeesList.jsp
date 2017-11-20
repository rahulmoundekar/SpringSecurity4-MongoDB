<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Employees</title>
</head>
<body>

	<h4>
		<a href="${pageContext.request.contextPath}/menu">Back to Menu</a>

	</h4>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="<c:url value="/j_spring_security_logout" />"> Logout</a>
		</h2>
	</c:if>
	
	<sec:authorize access="isAuthenticated()">
 Username: <sec:authentication property="principal.username" />
 Role: <sec:authentication property="principal.authorities" />
	</sec:authorize>
	<h1>List Employees</h1>
	<h3>
		<a href="${pageContext.request.contextPath}/employee/add">Add More Employee</a>
	</h3>

	<c:if test="${!empty employees}">
		<table align="left" border="1">
			<tr>
				<!-- <th>Employee ID</th> -->
				<th>Sr No</th>
				<th>Employee Name</th>
				<th>Employee Age</th>
				<th>Employee Salary</th>
				<th>Employee Address</th>
				<th>Actions on Row</th>
			</tr>

			<%int count=1; %>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<%-- <td><c:out value="${employee.id}"/></td> --%>
					<td><%=count++%></td>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.age}" /></td>
					<td><c:out value="${employee.salary}" /></td>
					<td><c:out value="${employee.address}" /></td>
					<td align="center"><a href="${pageContext.request.contextPath}/employee/edit?id=${employee.id}">Edit</a>
						| <a href="${pageContext.request.contextPath}/employee/delete?id=${employee.id}">Delete</a></td>
				
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>