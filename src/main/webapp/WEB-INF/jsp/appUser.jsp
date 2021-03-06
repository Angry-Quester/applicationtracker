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
        <h1>Application N</h1>
        <hr/>
        <c:if test="${not empty appUser}">
            <s:url var = "appUserApplications" value="/applications" />
            <a href="${appUserApplications}">User applications</a>
            <hr/>
                <dl  class="dl-horizontal">
                    <dt>appUserId</dt> <dd>${appUser.appUserId}</dd>
                    <dt>username</dt> <dd>${appUser.username}</dd>
                    <dt>password</dt> <dd>${appUser.password}</dd>
                    <dt>accountNonExpired</dt> <dd>${appUser.accountNonExpired}</dd>
                    <dt>accountNonLocked</dt> <dd>${appUser.accountNonLocked}</dd>
                    <dt>credentialsNonExpired</dt> <dd>${appUser.credentialsNonExpired}</dd>
                    <dt>enabled</dt> <dd>${appUser.enabled}</dd>
                </dl>
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