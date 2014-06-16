<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Functional view <c:out value="${NBapplications}"/> Applications</h2>
<div class="cadreNLA">

<table border='1' class="table-bordered " align="center" > 
	<thead>
		<th>nom</th>
		<th>prenom</th>
		<th>Adresse</th>
		<th>tel</th>
	</thead>
<c:forEach var="app" items="${account}">
	<tr>		
		<td><c:out value="${app.nom}"/></td>
		<td><c:out value="${app.prenom}"/></td>
		<td><c:out value="${app.adresse}"/></td>
		<td><c:out value="${app.tel}"/></td>
		<td><img alt="" src="img/actionPlanBuilder.jpg"></td>
		<td><img alt="" src="img/statistic.jpg"></td>
		<td><img alt="" src="img/techAnalysisAdmin.jpg"></td>
	</tr>
</c:forEach>
</table>
</div>