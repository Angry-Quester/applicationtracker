<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div>
<h1>Hardcore LOGIN DATA</h1>
<hr />
    <c:if test="${not empty loginData}">
        ${loginData}
    </c:if>
<hr />    
<h1>Hardcore CSRF token</h1>
    <p>${_csrf}</p>
    <p>"${_csrf.parameterName}"</p>
    <p>"${_csrf.token}"</p>
</div>