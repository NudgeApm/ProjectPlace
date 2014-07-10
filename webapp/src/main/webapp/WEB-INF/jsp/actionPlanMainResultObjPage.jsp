<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="cadreNLA">
<table class="table-striped table-bordered " >  
	<thead>
		<tr>		
			<th>PRI</th>
			<!-- >th>metricID</th-->
			<th>Object Full Name</th>
			<th>Metric Name</th>
			
			<!-- >th>objID</th-->
			<th>Weight</th>
				<th>Module</th>
				<th>Violation Status</th>				
				<!-- >th>HF</th-->
		</tr>
	</thead>
	<c:forEach var="obj" items="${objectList}">
		<tr>
		<td nowrap>${obj.pri}</td>
		<!-- >td nowrap>${obj.metricID}</td-->
		<td nowrap>${obj.objFullName}</td>
		<td nowrap>${obj.metricName}</td>		
		<!--  td nowrap>${obj.objID}</td-->
		
		<td nowrap>${obj.mWeight}</td>
		<td nowrap>${obj.moduleName}</td>
		<td nowrap>${obj.status}</td>
		
		<!-- >td nowrap>${obj.hf}</td-->
		
		</tr>
	</c:forEach>


</table>
</div>
