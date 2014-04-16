<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
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
        <s:url var = "loginPath" value="/loginTest" />
        <a href="${loginPath}">LOGIN DATA</a>    
        <hr />
        <s:url var = "testPath" value="/test" />
        <a href="${testPath}">test</a>
        
        тест русского текеста