<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.iiht.evaluation.coronokit.model.CoronaKit" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<% String user=(String) request.getSession().getAttribute("logUser"); 
CoronaKit kit=(CoronaKit)request.getAttribute("UserDetails");
request.getSession().setAttribute("UserDetails", kit);
%>
<jsp:include page="header.jsp"/>
<hr/>

	<h3>Hello ${UserDetails.personName}!</h3>
	<h4>Order Checkout</h4>
		<form action="user?action=placeorder" method="post">
			<table border="1" cellspacing="5px" cellpadding="5px" id="OrderCheckout">
				<tr id="rowCOHeader">
					<th>Product Id#</th>
					<th>Product Name</th>
					<th>Cost</th>
					<th>Description</th>
					<th>Choose Quantity</th>
					<th>Price</th>
					<th>Action</th>					
				</tr>
				<c:forEach items="${productMaster}" var="product">
					<tr id="rowCO${product.id}">
						<td>${product.id }</td>
						<td>${product.productName }</td>
						<td><label name="${product.id }.cost" id="${product.id }.cost" for="${product.cost}">${product.cost}</label></td>
						<td>${product.productDescription }</td>
						<td><input type="number" name="productQuantity" onchange="CalculatePrice('${product.id }.price','${product.id }.cost',this.value)" value="1"/>
						<input type="hidden" name="productList" value="${product.id }"/></td>
						<td><input name="${product.id }.price" value="${product.cost}" id="${product.id }.price" class="productPrice" readonly/></td>
						<td><input type="button" name="Delete" value="Delete Item" onClick="DeleteTableRow('rowCO${product.id}')"/></td>					
					</tr> 
				</c:forEach>
				<script type="text/javascript">
				function CalculatePrice(price,cost,qty){
					document.getElementById(price).value=document.getElementById(cost).textContent*qty
					var priceArr=document.getElementsByClassName('productPrice');
					var totQty=0;
					for(i=0;i<priceArr.length;i++){
						totQty=totQty + parseFloat(priceArr[i].value);
					}
					document.getElementById('totAmount').innerHTML = totQty;					
				}
				function DeleteTableRow(rowId){
					var row=document.getElementById(rowId);
					row.parentNode.removeChild(row);
				}
				</script>
			</table>
			<span><strong><b>Total Amount: </b></strong><label name="totAmount" id="totAmount" for="${totalCost}">${totalCost}</label></span>
			<br><span><input type="submit" value="Place Order" ></span>
		</form>
			<form action="index.jsp" method="post">
		<input type="submit" value="Logout" >
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>