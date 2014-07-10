<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
                    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>Dashboard Comparator Result</h2>
<!-- >style>  
.body {  background-image: url('./img/dashboardCompSum.jpg');  background-position: 300px 150px;  background-repeat: no-repeat;   font-weight: bold; }
.ADG_CQCANA {  position: absolute;  top: 22em;  left: 26em; }
</style>
<!-- body class='body' ><br><br><br><br-->

<table border='1' class="table-striped table-bordered " align="center" >
	<thead>
		<tr>
		<th>Central Bases</th>		
		<th colspan="2">Applications / Snapshot</th>		
		</tr>
	</thead>
	<tr>
		<td>central base 1:  ${schema.name}</td>
		<td colspan="2">
			<c:if test="${schema.numberOfSnapshot == 0}">
   				<i class='icon-minus'></i>
			</c:if>
            <c:if test="${schema.numberOfSnapshot > 0}">
		        <div class="controls">
	              <select id="snapshot1" >	              	
					<c:forEach var="snapshot" items="${schema.snapshotList}">
						<option value="<c:out value="${snapshot.id}"/>,<c:out value="${snapshot.appli.id}"/>"><c:out value="${snapshot.appli.name}"/>-<c:out value="${snapshot.name}"/></option>
					</c:forEach>
	              </select>
	            </div>
	    	</c:if>

		</td>
		
	</tr>
	<tr>
		<td>central base 2: ${schemaRef.name}</td>
		<td>
			<c:if test="${schemaRef.numberOfSnapshot == 0}">
   				<i class='icon-minus'></i>
			</c:if>
            <c:if test="${schemaRef.numberOfSnapshot > 0}">
		        <div class="controls">
	              <select id="snapshot2" >	              	
					<c:forEach var="snapshot" items="${schemaRef.snapshotList}">
						<option value="<c:out value="${snapshot.id}"/>,<c:out value="${snapshot.appli.id}"/>"><c:out value="${snapshot.appli.name}"/>-<c:out value="${snapshot.name}"/></option>
					</c:forEach>
	              </select>
	            </div>
	    	</c:if>
		</td>
		<td><span onclick="changePage()" ><i class='icon-th-large' ></i></span></td> 
	</tr>
</table>

<br><br>
<table border='1' class="table-striped table-bordered " align="center">
	<thead>
		<tr>
			<th>${schema.name}</th>
			<th>STANDARD</th>
		</tr>
	</thead>
	<c:forEach var="metric" items="${schema.removedMetric}">
		<tr>
			<td>${metric.name}</td>
			<td> - REMOVED - </td>
		</tr>
	</c:forEach>
	<c:forEach var="metric" items="${schema.addedMetric}">
		<tr>
			<td>&nbsp;</td>
			<td>(ADDED)${metric.name}</td>
		</tr>
	</c:forEach>
	<c:forEach var="metric" items="${schema.disabledMetric}">
		<tr>
			<td>(DISABLED)${metric.name}</td>
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
	<c:forEach var="metric" items="${schema.paramModifyMetric}">
		<tr>
			<td>(PARAM MODIFIED)${metric.name}</td>
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
</table>
List of Metric where Params have been changed between standard and current central base.
<table border='1' class="table-striped table-bordered " align="center">
	<thead>
		<tr>			
			<th>Metric Name</th>
			<th>${schema.name}</th>
			<th>STANDARD REF</th>
		</tr>
	</thead>
	<c:forEach var="metric" items="${schema.paramModifyMetricSTD}">
		<tr>
			<td>${metric.name}</td>
			<td>${metric.threholdHTML}</td>
			<td>${metric.threholdStandardHTML}</td>
		</tr>
	</c:forEach>
</table>




<script language="javascript">
// --- Insert current date into 'to' input
function changePage(){		
	schemaName="${schema.name}"
	schemaNameComp="${schemaRef.name}"
	snapshot1=document.getElementById("snapshot1").value
	snapshot2=document.getElementById("snapshot2").value
	
	document.location = "dashboardComparatorDetailMetric.htm?schemaName="+schemaName+"&schemaNameComp="+schemaNameComp+"&snapshotIDslashappliID1="+snapshot1+"&snapshotIDslashappliID2="+snapshot2;
}
</script>
