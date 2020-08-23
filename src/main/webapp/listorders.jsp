<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.iiht.evaluation.coronokit.model.CoronaKit" %>
<%@ page import="com.iiht.evaluation.coronokit.model.KitDetail" %>
<%@ page import="com.iiht.evaluation.coronokit.model.ProductMaster" %>
<%@ page import="com.iiht.evaluation.coronokit.model.OrderSummary" %>
<%@ page import="com.iiht.evaluation.coronokit.dao.KitDao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<font color="blue">Welcome <b>Admin!</b> </font>You are in Orders List page
<% 

KitDao kitDAO = (KitDao) request.getAttribute("kitDAO");
OrderSummary orderSummary=kitDAO.getOrderSummary();
List<KitDetail> kitDetails=orderSummary.getKitDetails();
CoronaKit kit=orderSummary.getCoronaKit();
//oid, kitId, pid, quantity, amount, personName, email, contactNo, address, orderDate, orderStatus 
%>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Kit Id</th>
					<th>Product Id</th>
					<th>Quantity</th>
					<th>Amount</th>	
					<th>Person Name</th>
					<th>Email</th>
					<th>Contact No</th>		
					<th>Address</th>
					<th>Order Date</th>
				</tr>
				<% for(KitDetail kitDetail: kitDetails) { 		
				%>
					<tr>
						<td><%=kitDetail.getCoronaKitId() %></td>
						<td><%=kitDetail.getProductId() %></td>
						<td><%=kitDetail.getQuantity() %></td>
						<td><%=kitDetail.getAmount()%></td>
						<td><%=kit.getPersonName() %></td>
						<td><%=kit.getEmail() %></td>
						<td><%=kit.getContactNumber() %></td>
						<td><%=kit.getDeliveryAddress() %></td>
						<td><%=kit.getOrderDate() %></td>
					</tr> 
				<% 	} %>
			</table>
	<a href="admin?action=list">Show Products</a>
	<form action="admin?action=logout" method="post">
		<input type="submit" value="Logout" >
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>