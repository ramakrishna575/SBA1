<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3>Welcome User!</h3>
<hr />
<h4>New User Registration</h4>
	<form action="user?action=insertuser" method="post">
		<table>
			<tr>
				<div>
					<td><label>Full Name</label></td>
					<td><input type="text" name="personName" required}/></td>
				</div>	
			</tr>
			<tr>
				<div>
					<td><label>Contact Number</label></td>
					<td><input type="text" name="contactNumber" required /></td>
				</div>	
			</tr>
			<tr>
				<div>
					<td><label>E-Mail</label></td>
					<td><input type="text" name="email" required /></td>
				</div>	
			</tr>
			<tr>
				<div>
					<td><label>Delivery Address</label></td>
					<td><input type="text" name="deliveryAddress" required /></td>
				</div>	
			</tr>	
		</table>
		<button>Create Corona Kit</button>		
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>