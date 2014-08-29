<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>détails des ventes</h2>
<div class="cadreNLA">
<table border='1' class="table-bordered " align="center" > 
	<thead>
		<th>nombre de ventes</th>
		
	</thead>
<c:forEach var="p" items="${products}">
	<tr>		
		<td><c:out value="${p.nbSales}"/></td>
		
	</tr>
</c:forEach>
</table>

</div>