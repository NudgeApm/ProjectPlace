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
<h2>Action Plan Builder</h2>
<div class="cadreNLA">

<table border='1' class="table-striped table-bordered " align="center" > 
	<thead>
		<tr>
		<th>Central Bases</th>		
		<th>Action plan summary</th>
		<th colspan="2" >Rules Tracking</th>
		<th colspan="2" >Objects Tracking</th>
		
		</tr>
	</thead>
	<thead>
		<tr>
		<th>&nbsp;</th>		
		<th># Objects</th>
		<th>Applications / Snasphots</th>
		<th># </th>
		<th>Applications / Snasphots</th>
		<th># </th>
		<th>Views</th>
		<th># </th>
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
		
		
		 <a href="actionPlanSummary.htm?schemaName=${schema.name}"><c:out value="${schema.numberOfAction}"/></a>
		
		
		</td>	
		<td> 
            <c:if test="${schema.numberOfSnapshot == 0}">
   				<i class='icon-minus'></i>
			</c:if>
            <c:if test="${schema.numberOfSnapshot > 0}">
		        <div class="controls">
	              <select id="${schema.name}" onchange="changePage('${schema.name}')"  >
	              	<option value=" " selected >&nbsp;</option>
					<c:forEach var="snapshot" items="${schema.snapshotList}">
						<option value="<c:out value="${snapshot.id}"/>,<c:out value="${snapshot.appli.id}"/>"><c:out value="${snapshot.appli.name}"/>(${snapshot.appli.id})-<c:out value="${snapshot.name}"/>(${snapshot.id})</option>
					</c:forEach>
	              </select>
	            </div>
	    	</c:if>
		</td>
		<td>
			<c:if test="${schema.numberOfSnapshot > 0}">
				<c:out value="${schema.numberOfSnapshot}"/>
			</c:if>
		</td>
		
		<td> 
            <c:if test="${schema.numberOfSnapshot == 0}">
   				<i class='icon-minus'></i>
			</c:if>
            <c:if test="${schema.numberOfSnapshot > 0}">
		        <div class="controls">
	              <select id="${schema.name}Obj" onchange="changePageObj('${schema.name}')"  >
	              	<option value=" " selected >&nbsp;</option>
					<c:forEach var="snapshot" items="${schema.snapshotList}">
						<option value="<c:out value="${snapshot.id}"/>,<c:out value="${snapshot.appli.id}"/>"><c:out value="${snapshot.appli.name}"/>-<c:out value="${snapshot.name}"/></option>
					</c:forEach>
	              </select>
	            </div>
	    	</c:if>
		</td>
		<td>
			<c:if test="${schema.numberOfSnapshot > 0}">
				<c:out value="${schema.numberOfSnapshot}"/>
			</c:if>
		</td>
		
		<td>
		<c:if test="${schema.numberOfEnlightenView == 0}">
   				<i class='icon-minus'></i>
			</c:if>
            <c:if test="${schema.numberOfEnlightenView > 0}">
		    <div class="controls">
              <select id="${schema.name}View"  onchange="changePageEnlighten('${schema.name}')" >
              	<option value=" " selected >&nbsp;</option>
				<c:forEach var="enlightenview" items="${schema.enlightenViewList}">
					<option value="<c:out value="${enlightenview.idmod}"/>"><c:out value="${enlightenview.modname}"/></option>
				</c:forEach>
              </select>
            </div>
            </c:if>
		</td>
		<td>
			<c:if test="${schema.numberOfEnlightenView > 0}">
				<c:out value="${schema.numberOfEnlightenView}"/>
			</c:if>
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
	document.location = "actionPlanMainResult.htm?schemaName="+param+"&snapshotIDslashappliID="+document.getElementById(param).value;
}

function changePageObj(param){
	var paramObj = param+"Obj";
	document.location = "actionPlanMainResultObj.htm?schemaName="+param+"&snapshotIDslashappliID="+document.getElementById(paramObj).value+"&hfForm=60014&weightForm=5&thresholdtForm=50&criticaltForm=false&moduleForm=&objNameForm=&metricNameForm=&metricIDForm=";
}

function changePageEnlighten(param){
	//alert("ok");
//	alert(param);
	//alert(document.getElementById(param).value);
	var paramView = param+"View";
	document.location = "actionPlanEnlightenResult.htm?schemaName="+param+"&snapshotIDslashappliID="+document.getElementById(paramView).value;
}

</script>

</body>
</html>
