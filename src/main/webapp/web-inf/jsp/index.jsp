<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<s:url var="cssCtx" value="/css"/>

<html>
	<head>
		<title>Welcome to the Application Tracker</title>
		<link href="${cssCtx}/html5-doctor-reset-stylesheet.css" rel="stylesheet" type="text/css" />
		<link href="${cssCtx}/style.css" rel="stylesheet" type="text/css" />

		<!-- 
		<link rel="stylesheet" href="css/html5-doctor-reset-stylesheet.css" type="text/css"/>
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
		 -->

	</head>
	<body>
		<p>Welcome Random User!</p>
		<p><c:if test="${!empty welcomeMessage}">${welcomeMessage}</c:if></p>
		<hr />
		<s:url var = "loginPath" value="/login" />
		<a href="${loginPath}">LOGIN</a>

	</body>
</html>

