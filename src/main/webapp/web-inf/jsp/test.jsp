<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
    <head>
        <title>Test page with Spring-MVC</title>
    </head>
    <body>
        <h1>Page</h1>

        <form action="<c:url value="/test"/>" method="post">
            <input type="text" name="testField" /> <br />
            <input type="submit" value="Push"/>
        </form>

        <c:if test="${not empty error}" >
            <p>${error}</p>]
          </c:if>


        <c:if test="${not empty data}" >
            <p>${data.testField}</p>
          </c:if>

    </body>
</html>

