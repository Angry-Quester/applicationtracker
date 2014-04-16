<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
        <c:if test="${not empty application}">
        <a href="${ctx}/applications">Все заявления</a>
        <hr/>
        <h1>Application номер ${application.applicationId}</h1>
        <hr/>

                <dl>
                    <dt>applicationId</dt> <dd>${application.applicationId}</dd>
                    <dt>givenName</dt> <dd>${application.givenName}</dd>
                    <dt>middleName</dt> <dd>${application.middleName}</dd>
                    <dt>familyName</dt> <dd>${application.familyName}</dd>
                    <dt>birthDate</dt> <dd>${application.birthDate}</dd>
                    <dt>creationDate</dt> <dd>${application.creationDate}</dd>
                    <dt>lastModificationDate</dt> <dd>${application.lastModificationDate}</dd>
                </dl>
        </c:if>
        <hr />
        <s:url var = "loginPath" value="/loginTest" />
        <a href="${loginPath}">LOGIN DATA</a>
        <hr />
        <s:url var = "testPath" value="/test" />
        <a href="${testPath}">test</a>
        
        тестовый текст