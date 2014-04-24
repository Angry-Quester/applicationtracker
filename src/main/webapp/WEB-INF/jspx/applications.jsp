<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
        <h1>Applications</h1>

        <hr />
        <p><a href="${ctx}/applications/new">Add application</a></p>
        <hr />

        <c:if test="${not empty applications}">
            <table class="table table-bordered">
                <tr>
                    <td>EDIT</td>
                    <td>DELETE</td>
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
                    <td><a href="${ctx}/applications/${application.applicationId}/edit">Edit</a></td>
                    <td>
                        <sf:form method="DELETE" action='${ctx}/applications/${application.applicationId}' id="deleteFor${application.applicationId}" >
                            <!--   <input type="submit" id="save" value="Delete" class="btn btn-default" /> -->
                            <a href="#" onclick="this.parentNode.submit();">Delete</a>
                        </sf:form>
                    </td>
                    <td><a href="${ctx}/applications/${application.applicationId}/txt">DOC :: ${application.applicationId}</a> ||
                        <a href="${ctx}/applications/${application.applicationId}">${application.applicationId}</a></td>
                    <td>${application.givenName}</td>
                    <td>${application.middleName}</td>
                    <td>${application.familyName}</td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy" value="${application.birthDate}" /></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy" value="${application.creationDate}" /></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy" value="${application.lastModificationDate}" /></td>
                </tr>
            </c:forEach>
            </table>
        </c:if>
        <hr />

        тест русского текеста