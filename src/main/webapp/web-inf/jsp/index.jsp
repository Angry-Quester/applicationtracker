<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<s:url var="cssCtx" value="/css"/>
<s:url var="jsCtx" value="/js"/>

<html>
	<head>
		<title>Welcome to the Application Tracker</title>
		<link href="${cssCtx}/html5-doctor-reset-stylesheet.css" rel="stylesheet" type="text/css" />
		<link href="${cssCtx}/bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="${cssCtx}/bootstrap-theme.css" rel="stylesheet" type="text/css" />		
		<link href="${cssCtx}/styles.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<p>Welcome Random User!</p>
		<p><c:if test="${!empty welcomeMessage}">${welcomeMessage}</c:if></p>
		<hr />
		<s:url var = "loginPath" value="/login" />
		<a href="${loginPath}">LOGIN</a>
		<table class="table table-striped table-bordered table-condensed table-condensed">
			<tr>
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
			</tr>
			<tr>
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
			</tr>
			<tr>
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
			</tr>
						<tr>
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
			</tr>
		</table>

		 <h1>Hello, world!</h1>
	
		<h1 id="h01"></h1>
		<span class="glyphicon glyphicon-search"></span>
		
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-star"></span> Star
		</button>

<div class="row">
  <div class="col-md-9 col-md-push-3">.col-md-9 .col-md-push-3</div>
  <div class="col-md-3 col-md-pull-9">.col-md-3 .col-md-pull-9</div>
</div>

		<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu3">
  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
  <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
</ul>
		<div class="btn-group">
  <button type="button" class="btn btn-default">Left</button>
  <button type="button" class="btn btn-default">Middle</button>
  <button type="button" class="btn btn-default">Right</button>
</div>

<!-- Split button -->
<div class="btn-group">
  <button type="button" class="btn btn-danger">Action</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li class="divider"></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>

		

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${jsCtx}/jquery-1.11.0.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${jsCtx}/bootstrap.min.js"></script>

	<script>
function myFunction()
		{
			$("#h01").attr("style","color:red").html("Hello jQuery")
		}
		$(document).ready(myFunction);
</script>

	</body>
</html>

