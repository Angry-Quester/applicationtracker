<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;"%>
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

        <c:if test="${not empty appUsers}">
            <table class="table table-bordered">
                <tr>
                    <td>appUserId</td>
                    <td>username</td>
                    <td>password</td>
                    <td>accountNonExpired</td>
                    <td>accountNonLocked</td>
                    <td>credentialsNonExpired</td>
                    <td>enabled</td>
                </tr>
            <c:forEach items="${appUsers}" var="appUser" varStatus="status">
                <tr>
                    <td><a href="${ctx}/appUsers/${appUser.appUserId}">${appUser.appUserId}</a></td>
                        <td>${appUser.appUserId}</td>
                        <td>${appUser.username}</td>
                        <td>${appUser.password}</td>
                        <td>${appUser.accountNonExpired}</td>
                        <td>${appUser.accountNonLocked}</td>
                        <td>${appUser.credentialsNonExpired}</td>
                        <td>${appUser.enabled}</td>
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