<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<s:url var="cssCtx" value="/css"/>
<s:url var="jsCtx" value="/js"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to the Application Tracker</title>
    <link href="${cssCtx}/html5-doctor-reset-stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${cssCtx}/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${cssCtx}/bootstrap-theme.css" rel="stylesheet" type="text/css" />
    <link href="${cssCtx}/styles.css" rel="stylesheet" type="text/css" />
</head>
<body id="body-test">
    <div id="content-background"></div>
    <div id="sidebar">
        <ol>
            <s:url var = "loginPath" value="/login" />
            <li><a href="${loginPath}">LOGIN Information</a></li>
            <s:url var = "testPath" value="/test" />
            <li><a href="${testPath}">test</a></li>
        </ol>
    </div>
    <div id="content">
        <h1>Page</h1>

  <center>
    <s:hasBindErrors name="testModel">
      <c:if test="${errors.errorCount > 0 }">
        <h4>Following errors need to be corrected:</h4>
        <font color="red">
          <c:forEach items="${errors.allErrors}" var="error">
            <s:message code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage} :: ${error.code}"/><br />
            <br />
            ${error.code} --- {error.arguments} --- ${error.defaultMessage}<br />
            <hr />
          </c:forEach>
        </font>
      </c:if>
    </s:hasBindErrors>
  </center>


        <hr/>

        <sf:form commandName="testModel" method="POST" action="${ctx}/test">

            <select id="testModel" name="testModel" class="form-control">
                <option value="">--select-something--</option>            
                    <c:forEach items="${testModelsList}" var="testModelItem" varStatus="Status">
                        <c:choose>
                            <c:when test="${testModel.testModel.testIntegerField eq testModelItem.testIntegerField}" >
                                <option value="${testModelItem.testIntegerField}" selected="selected">${testModelItem.testField}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${testModelItem.testIntegerField}">${testModelItem.testField}</option>
                            </c:otherwise>
                            </c:choose>
                    </c:forEach>
            </select>
            <sf:label path="testIntegerField">Binded testIntegerField</sf:label>
                <sf:input path="testIntegerField" class="form-control"/>
                    <sf:errors path="testIntegerField" />

            <sf:label path="testField">Binded testField</sf:label>
                <sf:input path="testField" class="form-control"/>
                    <sf:errors path="testField" />
            <hr />
            <c:forEach items="${testModel.testModels}" var="testModelItem" varStatus="status">
            
                <sf:input path="testModels[${status.index}].testIntegerField" class="form-control"/>
                    <sf:errors path="testModels[${status.index}].testIntegerField"/>
                    
                <sf:input path="testModels[${status.index}].testField" class="form-control"/>
                    <sf:errors path="testModels[${status.index}].testField"/>
                <p> ${status.index}</p>
                 
            </c:forEach>


            <input type="submit" value="Push" class="btn btn-default"/>

        <input type="hidden" 
            name="${_csrf.parameterName}"
            value="${_csrf.token}"/>

        </sf:form>

        <form action="" class="select-test">
            
        </form>

        <c:if test="${not empty errors}" >
            <p><c:forEach items="${errors}" var="singleError" varStatus="status">
                    <c:out value="<<<"/> ${status.index} <c:out value=">>>"/> :: ${singleError} <br />
                </c:forEach>
            </p>
        </c:if>

        <c:if test="${not empty err}" >
            ${err.allErrors}
        </c:if>


        <hr />
        <c:if test="${not empty string}">
            <p>Test Data Binding By Type :: ${string}</p>
        </c:if>
        <c:if test="${not empty appUserPrincipal}">
            <p>Test Data Binding By Type :: ${appUserPrincipal}</p>
        </c:if>
        <hr />

<!--  Test matherials-->

        <h1>DEMO</h1>
        <h1>Success !</h1>
        <h2>Some smart text</h2>
        <h2>Somethig is really Happening</h2>
        <h2>Somethig is really Happening</h2>
        <p> test</p>

        <table class="table table-bordered">
            <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
            </tr>
            <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
            </tr>
            <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
            </tr>
        </table>

         <h1>Hello, world!</h1>

        <h1 id="h01"></h1>
        <span class="glyphicon glyphicon-search"></span>

        <button type="button" class="btn btn-default btn-lg">
              <span class="glyphicon glyphicon-star"></span> Star
        </button>


        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu3">
  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
  <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
</ul>
        <div class="btn-group">
  <button type="button" class="btn btn-default">Left</button>
  <button type="button" class="btn btn-default">Middle</button>
  <button type="button" class="btn btn-default">Right</button>
</div>

<!-- Split button -->
<div class="btn-group">
  <button type="button" class="btn btn-danger">Action</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li class="divider"></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>
    <ul>
    </ul>

    </div>

<!--  Test matherials end-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${jsCtx}/jquery-1.11.0.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${jsCtx}/bootstrap.js"></script>

<script type="text/javascript">

var readyEvent = function () {
    $.getJSON( "applicationtypes", function( applicationTypes ) {
        var selectRows = [];

        for (var i=0; i<applicationTypes.length; i++) {
            selectRows.push("<option value=\""
                    + applicationTypes[i].applicationTypeId
                    + "\">" 
                    + applicationTypes[i].abbreviation
                    + " :: "
                    + applicationTypes[i].shortName
                    + " :: "
                    + applicationTypes[i].fullName
                    + "</value>");
        }

        
        $("<select/>", {"class": "form-control",
            html: selectRows.join( "" )
        }).appendTo( ".select-test" );

        console.log(selectRows.join( "" ));

    }); 
    
    console.log("alarma");
};

    $(document).ready(readyEvent); 

</script>


    </body>
</html>

