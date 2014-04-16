<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
        <p> Main Menu</p>
        <ol>
            <s:url var = "loginPath" value="/login" />
            <li><a href="${loginPath}">LOG IN</a></li>
            <s:url var = "loginPath" value="/logout" />
            <li><a href="${loginPath}">LOG OUT</a></li>
            <s:url var = "testPath" value="/loginTest" />
            <li><a href="${testPath}">login info</a></li>             
            <s:url var = "testPath" value="/test" />
            <li><a href="${testPath}">test</a></li>            
        </ol>
                тестовый текст