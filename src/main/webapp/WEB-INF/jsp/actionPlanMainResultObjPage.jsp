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
	<c:forEach var="objCAST" items="${objectList}">
		<tr>
		<td nowrap>${objCAST.pri}</td>
		<!-- >td nowrap>${objCAST.metricID}</td-->
		<td nowrap>${objCAST.objFullName}</td>
		<td nowrap>${objCAST.metricName}</td>		
		<!--  td nowrap>${objCAST.objID}</td-->
		
		<td nowrap>${objCAST.mWeight}</td>
		<td nowrap>${objCAST.moduleName}</td>
		<td nowrap>${objCAST.status}</td>
		
		<!-- >td nowrap>${objCAST.hf}</td-->
		
		</tr>
	</c:forEach>


</table>
</div>
