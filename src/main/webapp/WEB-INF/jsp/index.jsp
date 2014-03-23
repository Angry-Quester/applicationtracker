<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<html>
<head>
    <title><tiles:getAsString name="title" /></title>
    <link href="${cssCtx}/html5-doctor-reset-stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${cssCtx}/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${cssCtx}/bootstrap-theme.css" rel="stylesheet" type="text/css" />
    <link href="${cssCtx}/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="content-background"></div>
    <div id="header">
        <tiles:insertAttribute name="header"/>
    </div>

    <div id="sidebar">
        <tiles:insertAttribute name="sidebar"/>
    </div>
    <div id="content">
        <tiles:insertAttribute name="content"/>
    </div>
    <div id="footer">
        <tiles:insertAttribute name="footer"/>
    </div>
   
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${jsCtx}/jquery-1.11.0.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${jsCtx}/bootstrap.js"></script>
</body>
</html>

