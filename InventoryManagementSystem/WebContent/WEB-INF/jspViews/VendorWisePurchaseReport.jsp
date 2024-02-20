<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="jstlcore" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.accenture.lkm.business.bean.PurchaseBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<title>Vendor - Purchase Report</title>
<script type="text/javascript">
function callSubmit() {
	var fromDate = document.getElementById("fromdate").value;
    var toDate = document.getElementById("todate").value;
    
  
   var today = new Date();
   
   var year = today.getFullYear();
   var mon = today.getMonth()+1;
   var day = today.getDate();
   
   if (mon < 10) 
	   mon = '0' + mon;
   if (day < 10) 
	   day = '0' + day;
   
   var currDate = year+'-'+mon+'-'+day;
   var date = new Date(currDate);
  
   
   if((Date.parse(fromDate)) > date){
    	alert("From date should be today or past date");
    	 document.getElementById("fromdate").value = "";
        return;
    }


    if ((Date.parse(fromDate) > Date.parse(toDate))) {
        alert("To date should be today or future date");
        document.getElementById("todate").value = "";
      return;
    }
	 
	document.forms["form1"].action="getVendorWisePurchaseDetail.html";
	document.forms["form1"].submit();
}
</script>
</head>
<body>
<div class="container">
<jsp:include page="include.jsp" />
<h2 align="center">Vendor Purchase Report</h2>
</div>	
<f:form name="form1" modelAttribute="vendorWisePurchaseReportBean">

<table width="1000px" align="center" class="container">
				<tr>
					<td><b>Vendor Name:<font color="red">*</font> </b></td>
					<td><f:select path="vendorName">
						<f:option label="--Select--" value=""/>
							<f:options items="${vendorList}"  itemValue="vendorName" itemLabel="vendorName"/>
						</f:select>
					</td>
					
					<td><b>From Date:</b></td>
					<td><f:input path="fromDate"  onclick="javascript:NewCssCal('fromdate')" id="fromdate" /></td>

					<td><b>To Date:</b></td>
					<td><f:input path="toDate"  onclick="javascript:NewCssCal('todate')" id="todate" /></td>


					
					<td><input type="button" value="Search"
						onClick="javascript:callSubmit();" /></td>

				</tr>
				<tr>
					<td colspan="2"><font color="red"><f:errors	path="vendorName" style="font-family:sans serif;" /></font>
					</td>					
				</tr>
				

</table>

<br>

<jstlcore:if test="${!empty vendorBean}">
				
<table width="800px" class="container">
<tr>
<th>Address:</th>
<td>${vendorBean.vendorAddress}</td>
<th>Contact Number:</th>
<td>${vendorBean.contactNumber}</td>
<th>Contact Person:</th>
<td>${vendorBean.contactPerson}</td>
<td></td>
</tr>
</table>
</jstlcore:if>



<jstlcore:if test="${!empty vendorWisePurchaseBeanList}">
<%
				List<PurchaseBean> vendorWisePurchaseBeanList  = (ArrayList)session.getAttribute("vendorWisePurchaseBeanList");
				%>
			<jstlcore:set var="vendorWisePurchaseBeanList" scope="session" value="<%=vendorWisePurchaseBeanList%>" />
			<jstlcore:set var="totalCount" scope="session"
				value="<%=vendorWisePurchaseBeanList.size()%>" />
			<jstlcore:set var="perPage" scope="session" value="${5}" />
			<jstlcore:set var="pageStart" value="${param.start}" />
			<jstlcore:if test="${empty pageStart or pageStart < 0}">
				<jstlcore:set var="pageStart" value="0" />
			</jstlcore:if>
			<jstlcore:choose>
			<jstlcore:when test="${totalCount <= perPage}">
				<jstlcore:set var="pageStart" value="0" />
			</jstlcore:when>
			<jstlcore:when test="${totalCount == pageStart + perPage}">
				<jstlcore:set var="pageStart" value="${pageStart - perPage}" />
			</jstlcore:when>
			<jstlcore:when test="${totalCount < pageStart}">
				<jstlcore:set var="pageStart" value="${pageStart - perPage}" />
			</jstlcore:when>
			</jstlcore:choose>			
			<div class="container" align="right">
			<a href="?start=${pageStart - perPage}" class="next">&laquo;</a>
			${pageStart + 1} - 
			<jstlcore:choose>
			<jstlcore:when test="${totalCount < perPage}">
				${totalCount}
			</jstlcore:when>
			<jstlcore:when test="${totalCount < perPage + pageStart}">
				${totalCount}
			</jstlcore:when>
			<jstlcore:otherwise>
				${pageStart + perPage} 
			</jstlcore:otherwise>
			</jstlcore:choose>
			
			
    		<a href="?start=${pageStart + perPage}"  class="next">&raquo;</a>
    		</div>
	<TABLE id="dataTable" border="1" class="container">
	<tr class="tr1">
		<th>S.No</th>
		<th>Material Category</th>
		<th>Material Type</th>
		<th>Brand</th>
		<th>Quantity</th>
		<th>Unit</th>
		<th>Price (&#8377;)</th>
		<th>Balance (&#8377;)</th>
		<th>Purchase Date</th>
	</tr>
	<jstlcore:forEach items="${vendorWisePurchaseBeanList}" var="vendorWisePurchaseBeanList" begin="${pageStart}" end="${pageStart + perPage - 1}" varStatus="i">
	<jstlcore:set var = "j" value="${i.index}" />
		<tr>
		<td align="center">${j+1}</td>
		<td align="center"><jstlcore:out value="${vendorWisePurchaseBeanList.materialCategoryName}"></jstlcore:out></td>
		<td align="center"><jstlcore:out value="${vendorWisePurchaseBeanList.materialTypeName}"></jstlcore:out></td>
		<td align="center"><jstlcore:out value="${vendorWisePurchaseBeanList.brandName}"></jstlcore:out></td>
		<td align="center"><jstlcore:out value="${vendorWisePurchaseBeanList.quantity}"></jstlcore:out></td>
		<td align="center"><jstlcore:out value="${vendorWisePurchaseBeanList.materialUnitName}"></jstlcore:out></td>
		<td align="center"><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2" value="${vendorWisePurchaseBeanList.purchaseAmount}" /></td>
		<td align="center"><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2" value="${vendorWisePurchaseBeanList.purchaseAmount}" /></td>	
		<td align="center">
		<fmt:formatDate value="${vendorWisePurchaseBeanList.purchaseDate}" pattern="dd-MMM-yyyy" type="DATE"/>
		</td>
		</tr>
	</jstlcore:forEach>
	</TABLE>
	</jstlcore:if>
	<h4 align="center" style="color: red">${message}</h4>
</f:form>

<div class="terms2">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2023 Accenture All Rights Reserved.</p>
</div>
</body>
</html>