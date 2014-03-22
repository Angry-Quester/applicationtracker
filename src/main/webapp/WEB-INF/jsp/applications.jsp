<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

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
    <div id="content-background"></div>
    <div id="header">
        <p>Header</p>
        <div id="pic"></div>
    </div>

    <div id="sidebar">
        menu text
    </div>
    <div id="content">

        <h1>Applications</h1>
        
        <c:if test="${not empty applications}">
            <table class="table table-bordered">
                <tr>
                    <td>applicationId</td>
                    <td>givenName</td>
                    <td>middleName</td>
                    <td>familyName</td>
                    <td>birthDate</td>
                    <td>creationDate</td>
                    <td>lastModificationDate</td>
                </tr>
            <c:forEach items="${applications}" var="application" varStatus="status">
                <tr>
                    <td><a href="${ctx}/applications/${application.applicationId}">${application.applicationId}</a></td>
                    <td>${application.givenName}</td>
                    <td>${application.middleName}</td>
                    <td>${application.familyName}</td>
                    <td>${application.birthDate}</td>
                    <td>${application.creationDate}</td>
                    <td>${application.lastModificationDate}</td>
                </tr>
            </c:forEach>
            </table>
        </c:if>
        <hr />
        <s:url var = "loginPath" value="/login" />
        <a href="${loginPath}">LOGIN</a>
        <hr />
        <s:url var = "testPath" value="/test" />
        <a href="${testPath}">test</a>

    </div>
    <div id="footer">
        <p>Footer</p>
    </div>
   
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${jsCtx}/jquery-1.11.0.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${jsCtx}/bootstrap.js"></script>
</body>
</html>