<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CAST LOG investigation</title>
</head>
<body>
<form:form method="POST" commandName="castLog">
	<table>
		<tr>
			<td>Cast Log file:</td>
			<td><form:input path="fileName" /></td>
		</tr>		
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form:form>
</body>
</html>