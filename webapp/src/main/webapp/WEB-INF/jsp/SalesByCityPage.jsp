<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>bilan des ventes</h2>
<div class="cadreNLA">
Total des ventes : <c:out value="${nbSales}"/>
<table border='1' class="table-bordered " align="center" > 
	<thead>
		<th>Produit</th>
		<th>Ville</th>
		<th># ventes</th>
		
	</thead>
<c:forEach var="s" items="${sales}">
	<tr>		
		<td>
		<a href="afficheDetail.htm?idCity=${s.idCity}&idProduct=${s.idProduct}">
		          <i class="icon-info-sign"></i><c:out value="${s.product}"/>
		      </a>
		</td>
		<td><c:out value="${s.city}"/></td>
		
		<td><c:out value="${s.numberOfSales}"/></td>
	</tr>
</c:forEach>
</table>

</div>