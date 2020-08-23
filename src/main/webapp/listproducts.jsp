<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.iiht.evaluation.coronokit.model.CoronaKit" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(${logUser=='admin'?'Admin':'User'})</title>
</head>
<body>
<% String user=(String) request.getSession().getAttribute("logUser"); 
CoronaKit kit=(CoronaKit)request.getAttribute("OrderDetails");
request.getSession().setAttribute("UserDetails", kit);
%>
<jsp:include page="header.jsp"/>
<hr/>
	<% if("user".equals(user)) { %>
		<form action="user?action=showproducts" method="post">
		<div>Hello ${OrderDetails.personName}!</div>
		<input type="hidden" name="OrderDetails" value="<%= request.getAttribute("OrderDetails")%>">
	<% } else{%>
	<font color="blue">Welcome <b>Admin!</b> </font>You are in Product Master page
	<%} %>
	<c:set var="OrderDetails" scope="request" value="${OrderDetails}" />
	<c:choose>
		<c:when test="${productMaster.isEmpty()}">
			<p>No Products Available</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Product Id#</th>
					<th>Product Name</th>
					<th>Cost</th>
					<th>Description</th>
					<th>${logUser=='admin'?'Action':'Choose'}</th>					
				</tr>
				<c:forEach items="${productMaster}" var="product">
				<c:set var="id" value="${product.id}" />
					<tr>
						<td>${product.id }</td>
						<td>${product.productName }</td>
						<td>${product.cost }</td>
						<td>${product.productDescription }</td>
						<td>
						<% if("admin".equals(user)) { %>
							<span><a href="admin?action=editproduct&id=${product.id}">EDIT</a></span>
							<span><a href="admin?action=deleteproduct&id=${product.id}">DELETE</a></span>
						<% } else {%>
							<input type="checkbox" name="productList" value="${product.id}"/>
						<% } %>
						</td>
					</tr> 
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<% if("admin".equals(user)) { %>
		<a href="admin?action=insertproduct">Add New Product</a>
		<a href="admin?action=listorders">Show Orders</a>
		<form action="admin?action=logout" method="post">
		<input type="submit" value="Logout" >
		</form>
	<% } else {%>
		<input type="submit" value="Choose Quantity" >
		</form>
	<%} %>


<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>