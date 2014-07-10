<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
                    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>   
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>  
</head>
<body>
<h2>Dashboard Comparator Builder</h2>
<div class="cadreNLA">

<table border='1' class="table-striped table-bordered " align="center" > 
	<thead>
		<tr>
		<th>Central Bases</th>		
		<th>Reference</th>
		<th>&nbsp;</th>
		</tr>
	</thead>
	<thead>
		<tr>
		<th>&nbsp;</th>		
		<th>Central Base</th>
		<th>Validate</th>
		
		</tr>
	</thead>
<c:forEach var="schema" items="${cbList.listeSchemaCb}">
	<tr>
		<td>
		 <div class="accordion-group">
		    <div class="accordion-heading">
		    	<table border='0' ><tr><td> 
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne${schema.name}">
		          <i class="icon-info-sign"></i>
		      </a>
		      </td>
		      <td>${schema.name}</td>
		      </tr></table>
		    </div>
		   
		  </div>
		   <div id="collapseOne${schema.name}" class="accordion-body collapse">
		      <div class="accordion-inner">
		        Version: ${schema.version}
		      </div>
		    </div>		  
		 </td> 	
		<td> 
          <div class="controls">
          <select id="${schema.name}"  >
          	<option value=" " selected >&nbsp;</option>
				<c:forEach var="schemaRef" items="${cbListRef.listeSchemaCb}">
					<c:if test="${schema.name != schemaRef.name}">
   				<option value="<c:out value="${schemaRef.name}"/>">${schemaRef.name}-${schemaRef.version}</option>
			</c:if>
					
				</c:forEach>		
          </select>
        </div>
	
		</td>
		<td>
			<span onclick="changePage('${schema.name}')" ><i class='icon-play' ></i></span>
		</td>
	</tr>
</c:forEach>
</table>
<table>
</table>
</div>
<script language="javascript">
// --- Insert current date into 'to' input
function changePage(param){	
	document.location = "dashboardComparatorCompare.htm?schemaName="+param+"&schemaNameRef="+document.getElementById(param).value;
}
</script>

</body>
</html>