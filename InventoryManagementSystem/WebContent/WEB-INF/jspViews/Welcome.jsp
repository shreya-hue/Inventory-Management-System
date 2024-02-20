<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<meta name="viewport" content="width=auto, initial-scale=1">
</head>
<body>
<div class="container">

<jsp:include page="include.jsp" />
<h1>Problem Statement</h1>

<p>
ABC, a company needs an application to manage their inventory details. This application focuses on purchasing various materials needed from vendors and maintains their stocks. 

This application helps to generate reports on:
<ul>
<li>Showing relevant details on date wise materials purchased for all vendors.</li>
<li>Showing relevant details of the materials purchased based on the selected vendor, additionally the user may filter the report by specifying From and To dates.</li>
<li>Showing relevant details of the payment history of a particular vendor between From and To Dates on the basis of : balance pending, fully paid or overpaid status on the materials purchased,
Specific purchase id</li>
</ul>

</p>
</div>
<div class="terms1">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2023 Accenture All Rights Reserved.</p>
</div>
</body>
</html>
