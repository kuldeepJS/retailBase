<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<table>
		<tr>
			<td>Welcome to goods4u.in Admin panel</td>
		</tr>
		<tr>
			<td>
				<form action="./login.jsp">
					<table>
						<tr>
							<td>User name:</td>
							<td><input type="text" name="aname"/></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="awd"/></td>
						</tr>
						<tr>
							<td><a href="./forgotAPassword.jsp" target="_blank">Forgot passsword?</a></td>
							<td><input type="submit" name="sbAdmin" value="LOGIN"/></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>Designed by Innovative Software Labs. All rights reserved-2014</td>
		</tr>
	</table>
</body>
</html>