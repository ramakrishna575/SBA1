<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

	<h3>Hello ${UserDetails.personName}!</h3>
	<h4>Order Summary</h4>
	<br>
	<font color="green">Order placed successfully!</font>
	<br>
	<a href="user?action=ordersummary&id=${OID}">Click here to view order summary</a>
	<form action="index.jsp" method="post">
		<input type="submit" value="Logout" >
	</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>