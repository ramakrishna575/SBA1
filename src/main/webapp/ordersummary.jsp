<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.iiht.evaluation.coronokit.model.CoronaKit" %>
<%@ page import="com.iiht.evaluation.coronokit.model.KitDetail" %>
<%@ page import="com.iiht.evaluation.coronokit.model.ProductMaster" %>
<%@ page import="com.iiht.evaluation.coronokit.model.OrderSummary" %>
<%@ page import="com.iiht.evaluation.coronokit.dao.ProductMasterDao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3>Order summary</h3>
<hr />
<% OrderSummary orderSummary=(OrderSummary)request.getAttribute("OrderSummary");
List<KitDetail> kitDetails=orderSummary.getKitDetails();
CoronaKit kit=orderSummary.getCoronaKit();
ProductMasterDao pmDAO = (ProductMasterDao) request.getAttribute("pmDAO");
%>

<br>
<b><strong>Delivery Address:</strong></b>
<br>
<%= kit.getDeliveryAddress() %>
<br>
Contact# <%= kit.getContactNumber() %>
Order Date: <%= kit.getOrderDate() %>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Product Id</th>
					<th>Product Name</th>
					<th>Product Quantity</th>
					<th>Product Cost</th>
					<th>Amount</th>					
				</tr>
				<% for(KitDetail kitDetail: kitDetails) { 
					ProductMaster pm;
					try{
							pm=pmDAO.getById(kitDetail.getProductId());					
				%>
					<tr>
						<td><%=kitDetail.getProductId() %></td>
						<td><%=pm.getProductName() %></td>
						<td><%=kitDetail.getQuantity() %></td>
						<td><%=pm.getCost() %></td>
						<td><%=kitDetail.getAmount() %></td>
					</tr> 
				<% }catch(Exception e){}
				} %>
			</table>
<br>
<b><strong>Total Amount: <font color="blue"><%= kit.getTotalAmount() %></font></strong></b>
	<form action="index.jsp" method="post">
		<input type="submit" value="Logout" >
	</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>