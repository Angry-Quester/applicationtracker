<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
    <h1>Select application type you would like to create</h1>
        <ul>
            <c:forEach items="${applicationTypes}" var="applicationType">
                <li><a href="${ctx}/applications/new/${applicationType.applicationTypeId}">${applicationType.abbreviation} -- ${applicationType.shortName}</a></li>
            </c:forEach>
        </ul>