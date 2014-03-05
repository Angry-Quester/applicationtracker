<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Test page with Spring-MVC</title>
		<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
	</head>
	<body>
		<h1>Login Page</h1>
		<c:if test="${not empty data}" >
			<p>${data}</p>
		</c:if>
		<hr/>
		<c:if test="${not empty loginData}" >
			<p>${loginData}</p>
		</c:if>
	</body>
</html>

