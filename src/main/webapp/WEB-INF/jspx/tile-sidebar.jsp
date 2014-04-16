<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
        <p> Main Menu</p>
        <ol>
            <s:url var = "loginPath" value="/login" />
            <li><a href="${loginPath}">LOG IN</a></li>
            <s:url var = "logoutPath" value="/logout" />
            <li><a href="${logoutPath}">LOG OUT</a></li>
            <s:url var = "testPath" value="/loginTest" />
            <li><a href="${testPath}">Login Data</a></li>
            <hr />
            <s:url var = "applicationsPath" value="/applications" />
            <li><a href="${applicationsPath}">Applications</a></li>            
            <hr />
            <s:url var = "testPath" value="/test" />
            <li><a href="${testPath}">test</a></li>            
        </ol>
                тестовый текст