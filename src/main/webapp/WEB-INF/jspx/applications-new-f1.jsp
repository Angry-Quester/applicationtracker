<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
    <h1>Add new Application</h1>
        <h1>Форма ф1 (контракт) </h1>
        <sf:form commandName="application" method="POST" action="${ctx}/applications" >
            <sf:label path="givenName">GIVENNAME</sf:label>
                <sf:input path="givenName" class="form-control"/>
                    <sf:errors path="givenName" />
            <sf:label path="middleName">MIDDLENAME</sf:label>
                <sf:input path="middleName" class="form-control"/>
                    <sf:errors path="middleName" />
            <sf:label path="familyName">FAMILYNAME</sf:label>
                <sf:input path="familyName" class="form-control"/>
                    <sf:errors path="familyName" />
            <sf:label path="birthDate">BIRTHDATE</sf:label>
                <sf:input path="birthDate" class="form-control"/>
                    <sf:errors path="birthDate" />
            <sf:label path="creationDate">CREATIONDATE</sf:label>
                <sf:input path="creationDate" class="form-control"/>
                    <sf:errors path="creationDate" />
            <sf:label path="lastModificationDate">LASTMODIFICATIONDATE</sf:label>
                <sf:input path="lastModificationDate" class="form-control"/>
                    <sf:errors path="lastModificationDate" />
            <sf:label path="cField">cField</sf:label>
                <sf:input path="cField" class="form-control"/>
                    <sf:errors path="cField" />                    

            <input type="submit" id="save" value="Save" class="btn btn-default" />
        </sf:form>