<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

    <h1>LOGIN FORM</h1>
    
    <form action="${ctx}/j_spring_security_check" method="POST" name="f">
        <c:if test="${not empty error}">
            <p>
                ${error}
            </p>
        </c:if>
        <c:if test="${not empty logout}">
            <p>
                ${logout}
            </p>
        </c:if>
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="j_username" placeholder="Username"/>

            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="j_password" placeholder="Password"/>

        <input type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"/>
            
        <button type="submit" class="btn btn-default">LOG IN</button>
    </form>
    
    <hr />

    <a href="${ctx}">INDEX</a> <br />
    <a href="${ctx}/applications">APPLICATIONS</a> <br />
    
    <hr />
