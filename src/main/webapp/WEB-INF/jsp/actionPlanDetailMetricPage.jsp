<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
var violationList = new Array();
var schemaName='<c:out value="${schemaName}"/>';
var metricID='<c:out value="${metricID}"/>';

var snapshotIDslashappliID='<c:out value="${snapshotIDslashappliID}"/>';

var mViolation;
var ind=0;

function violation(pName,pLoc,pFilePath,pModule,pId,pSelectedForAP) {
    this.name=pName;
    this.loc=pLoc;
    this.filePath=pFilePath;
    this.module=pModule;
    this.id=pId;
    this.selectedForAP=pSelectedForAP;
}
 
<c:forEach var="violation" items="${violationList}">
	mViolation = new violation('${violation.name}','${violation.loc}','${violation.filePath}','${violation.module}','${violation.objId}','${violation.selectedForAP}');
	violationList[ind]=mViolation;
	ind = ind +1;	
</c:forEach>
</script>

<div class="cadreNLA">
<table class="table-nla" width="900px" >  
	<thead>
		<tr>
			<th ><input id='objectIDListSelectAll' type='checkbox' onclick='selectAllCheckBox()' > Object name</th>
			<th>Line</th>
			<th>File path</th>
			<th>Module</th>
		</tr>
	</thead>
	
	<script type="text/javascript">
	for (var i=0;i<=(violationList.length-1);i=i+1){
		document.write("<tr>");		
		
		
		//document.write("<td><input id='objectIDList"+violationList[i].id+"' type='checkbox' value='"+violationList[i].id+"'></td>");
		
		document.write("<td>");
		if(violationList[i].selectedForAP == 'true'){
			document.write("<a href='actionPlanDeleteFromAPFromDetailMetric.htm?snapshotIDslashappliID="+snapshotIDslashappliID+"&schemaName="+schemaName+"&metricID="+metricID+"&ObjID="+violationList[i].id+"'>");
			document.write("<i class='icon-check'></i><b>"+violationList[i].name);
			document.write("</b></a>");
		}else{
			
			document.write("<div class='accordion-heading'>"); 
			document.write("<table><tr><td><input id='objectIDList"+violationList[i].id+"' type='checkbox' value='"+violationList[i].id+"'></td><td><a class='accordion-toggle' data-toggle='collapse' data-parent='#accordion2' href='#collapseOne"+violationList[i].id+"'>");
			document.write(""+violationList[i].name);
			document.write("</a></td></tr></table>");
			document.write("</div>");
			document.write("<div id='collapseOne"+violationList[i].id+"' class='accordion-body collapse'>");
			document.write("<div class='accordion-inner'>");
			
			document.write("<form class='well' id='theForm"+violationList[i].id+"' action='#' >");
			document.write("<table>");
			document.write("<tr>");
			document.write("<td><label>Priority</label></td>");
			document.write("<td>  	<select id='priorityInput"+violationList[i].id+"' >");
			document.write("<option value='4' >Low</option>");
			document.write("<option value='3' >Moderate</option>");
			document.write("<option value='2' >High</option>");
			document.write("<option value='1' selected >Extreme</option>");
			document.write("</select>");
			document.write("</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td><label>Comment</label></td>");
			document.write("<td><input id='commentInput"+violationList[i].id+"' type='text' value='' /></td>");
			document.write("</tr>");
			document.write("</table>");
			document.write(" <input id='Button1' type='button' onclick='valideForm("+violationList[i].id+")' value='Add to Action plan' class='btn' />");	
			document.write("</form>");
			
			
			
			document.write("</div>");
			document.write("</div>");
			document.write("</div>");
		}
		//document.write("");
		document.write("	</td>");
		document.write("	<td>"+violationList[i].loc+"</td>");
		document.write("	<td>"+violationList[i].filePath+"</td>");
		document.write("	<td>"+violationList[i].module+"</td>");	
		document.write("</tr>");
	}
	
	function selectAllCheckBox(){
		if(document.getElementById("objectIDListSelectAll").checked){
			for (var i=0;i<=(violationList.length-1);i=i+1){
				objID = "objectIDList"+violationList[i].id;				
				if(document.getElementById(objID) != null){
					document.getElementById(objID).checked=true;	
				}
				
			}
		}else{
			for (var i=0;i<=(violationList.length-1);i=i+1){
				objID = "objectIDList"+violationList[i].id;
				if(document.getElementById(objID) != null){
					document.getElementById(objID).checked=false;
				}
			}
		}
	}
	
	function valideFormMultipleObj(){
		var formPriority = "#priorityInputAll";
		priorityForm = $(formPriority).attr("value");
		var formComment = "#commentInputAll";
		commentForm = $(formComment).attr("value");
		
		objList = "";
		for (var i=0;i<=(violationList.length-1);i=i+1){
			objID = "objectIDList"+violationList[i].id;
			if(document.getElementById(objID) != null && document.getElementById(objID).checked){
				if(objList== ""){
					objList=violationList[i].id;
				}else{
					objList = objList + "," + violationList[i].id;
				}
			
			}
		}
		
		
		document.location = "actionPlanDetailMetricAddObjList.htm?schemaName="+schemaName+"&snapshotIDslashappliID="+snapshotIDslashappliID+"&metricID="+metricID+"&objIDList="+objList+"&priority="+priorityForm+"&comment="+commentForm;
	}
	
	function valideForm(objID){
		var formPriority = "#priorityInput"+objID;
		priorityForm = $(formPriority).attr("value");
		var formComment = "#commentInput"+objID;
		commentForm = $(formComment).attr("value");
		document.location = "actionPlanDetailMetricAddObj.htm?schemaName="+schemaName+"&snapshotIDslashappliID="+snapshotIDslashappliID+"&metricID="+metricID+"&objID="+objID+"&priority="+priorityForm+"&comment="+commentForm;
	}
	
	</script>
</table>
</div>
 <!-- 
<a href='actionPlanDeleteFromAPFromDetailMetric.htm'></a>
<a href='actionPlanDetailMetricAddObj.htm'></a>
 -->
