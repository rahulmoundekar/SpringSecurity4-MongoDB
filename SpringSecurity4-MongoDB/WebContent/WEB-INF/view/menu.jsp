<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<body>
		<h2 id="banner">Welcome to Spring Security MongoDB Demo</h2>
		
		<!-- <h2>Your user info</h2>
		 <h6><a href="j_spring_security_logout">Click here to logout</a></h6> -->
		 
		 <sec:authorize access="isAuthenticated()">
		 Username: <sec:authentication property="principal.username" />
		 Role: <sec:authentication property="principal.authorities"/>
		 </sec:authorize>
		
		<h3 id="banner">MENU</h3>  
		<p class="message"></p>
		<a href="${pageContext.request.contextPath}/listUsers">Users</a> 

		<p class="message"></p>
		<a href="${pageContext.request.contextPath}/listCampaigns">Campaigns</a> 
	
		<h2>1. <a href="${pageContext.request.contextPath}/employee/employees">List of Employees</a></h2>
    	<h2>2. <a href="${pageContext.request.contextPath}/employee/add">Add Employee</a></h2>
		
		
		<p class="message"></p>
		<a href="${pageContext.request.contextPath}/logout">Log-out</a>
		 
	</body>
</html>