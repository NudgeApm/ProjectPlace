<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
                    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success Page</title>
</head>
<body>
File Details  
<hr>
filename : ${log.fileName} <br/>
<br>
Central Bases<br>

<table border='1'> 
	<tr>
		<td>Central Bases</td>
		<td>Version</td>
	</tr>
<c:forEach var="schema" items="${log.listeSchemaCb}">
	<tr>
		<td><a href="detailCentral.htm?schemaName=<c:out value="${schema.name}"/>"><c:out value="${schema.name}"/></a></td>
		<td><c:out value="${schema.version}"/></td>
	</tr>
</c:forEach>
</table>

Knowledge Bases<br>
<c:forEach var="schema" items="${log.listeSchemaKb}">
	- <c:out value="${schema.name}"/><br>
</c:forEach>

</body>
</html>
