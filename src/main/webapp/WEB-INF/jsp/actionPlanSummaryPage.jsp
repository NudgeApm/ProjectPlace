<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
var metricList = new Array();
var mMetric;
var ind=0;

var actionList = new Array();
var mAction;
var indAct=0;

function metric(pRuleName,pNbAction, pId) {
	this.id=pId;
    this.ruleName=pRuleName;
    this.nbAction=pNbAction;
}
 
function actionCAST(pMetricID,pObjName,pObjID) {
	this.metricID=pMetricID;
    this.objName=pObjName;
    this.objID=pObjID;
}

<c:forEach var="metric" items="${metricList}">
	mMetric = new metric('${metric.name}','${metric.nbAction}','${metric.id}');
	metricList[ind]=mMetric;
	ind = ind +1;	

	<c:forEach var="actionCAST" items="${metric.actionList}">
		mAction = new actionCAST('${actionCAST.ruleId}','${actionCAST.objectFullName}','${actionCAST.objectId}');
		actionList[indAct]=mAction;
		indAct = indAct + 1;
	</c:forEach>
	
	
</c:forEach>
</script>

<div id="divNLA">
Action plan summary
<table class="table-nla" width="900px" >  
	<thead>
		<tr>
			<th>Metric name</th>
			<th># violations</th>
		</tr>
	</thead>
	
	<script type="text/javascript">
	for (var i=0;i<=(metricList.length-1);i=i+1){
		document.write("<tr>");
		document.write("	<td>");
		
		
		document.write("<div class='accordion-group'>");
		document.write("<div class='accordion-heading'>");
		document.write("<table ><tr><td><a href='actionPlanDeleteFromAP.htm?schemaName=<c:out value="${schemaName}"/>&metricID="+metricList[i].id+"&ObjID=0'><i class='icon-remove-sign' ></i></a>");
		document.write("</td><td><a class='accordion-toggle' data-toggle='collapse' data-parent=''#accordion2' href='#collapseOne"+i+"'>");
		document.write(metricList[i].ruleName);
		document.write("</a></td></tr></table>");
		document.write("</div>");
		document.write("<div id='collapseOne"+i+"' class='accordion-body collapse'>");
		document.write("  <div class='accordion-inner'>");
		for (var j=0;j<=(actionList.length-1);j=j+1){
			if(actionList[j].metricID == metricList[i].id){
				document.write("<a href='actionPlanDeleteFromAP.htm?schemaName=<c:out value="${schemaName}"/>&metricID="+metricList[i].id+"&ObjID="+actionList[j].objID+"'><i class='icon-remove-sign' ></i></a>"+actionList[j].objName+"<br>");	
			}
		}
		
		document.write("</div>");
		document.write("</div>");
		document.write("</div>");
		document.write("</td>");
		
		document.write("	<td>"+metricList[i].nbAction+"</td>");	
		document.write("</tr>");
	}
	</script>
</table>
</div>

<!-- 
FOR CAST
<a href='actionPlanDeleteFromAP.htm'></a>
 -->
