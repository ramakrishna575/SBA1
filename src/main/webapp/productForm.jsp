<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h3>${isNew?'New Product':'Edit Product' }</h3>

	<form action="admin?action=${isNew?'newproduct':'updateproduct'}" method="post">
		<div>
			<label>Product Id</label>
			<input type="number" name="pId" value="${product.id}" required ${isNew?'':'readonly' }/>
		</div>	
		<div>
			<label>Product Name</label>
			<input type="text" name="pName" value="${product.productName}" required />
		</div>	
		<div>
			<label>Product Cost</label>
			<input type="decimal" name="pCost" value="${product.cost}" required />
		</div>	
		<div>
			<label>Product Description</label>
			<input type="text" name="pDesc" value="${product.productDescription}" required />
		</div>		
		<button>SAVE</button>		
	</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>