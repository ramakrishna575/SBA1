<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Home</title>
</head>
<body>
<div>
<jsp:include page="header.jsp"/>
<hr/>
	<h2>Admin Login</h2>
	<form action="admin?action=login" method="post">
		<% if("failed".equals(request.getParameter("action"))) { %>
		<font color=red>Either user name or password is wrong.</font>
		<% } else if("logout".equals(request.getParameter("action"))) { %>
		<font color=green>You have successfully logged out!</font>
		<% } else {%>
		<font color=blue>Welcome!</font>
		<% } %>
		<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid"> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="text" id="password" name="password"> </div>
		</div>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
	</form>
</div>
<hr/>
<div>
	<a href="user?action=newuser"><button>Create Corona Kit</button></a>
</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>