<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
var schemaName='${schemaName}';
var snapshotIDslashappliID='${snapshotIDslashappliID}';
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Objects Tracking</h2>
<div class="accordion-group">
		    <div class="accordion-heading">
		      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
		        <i class="icon-search"></i>Search Filters
		      </a>
		    </div>
		    <div id="collapseOne" class="accordion-body collapse">
		      <div class="accordion-inner">
<table width="100%">
<tr><td>

<form class="well" id="testForm" action="actionPlanMainResultObj.htm" >
 <table >
 	<tr>
 		<td><label>Health factor</label></td>
 		<td>
 			<select id="healthFactorInput" >
				<option value="60014" >Performance</option>
				<option value="60016" >Security</option>
				<option value="60013" >Robustness</option>
			</select>
 		</td>
 		<td>Module</td>
 		<td><input id="moduleInput" type="text" value="" /></td>
 	</tr>
 	<tr>
 		<td><label>Weight(Minimum weight for rule)</label></td>
 		<td>
 			<select id="weightInput" >
				<option value="5" >5</option>
				<option value="6" >6</option>
				<option value="7" >7</option>
				<option value="8" >8</option>
				<option value="9" >9</option>
			</select>
		</td>
 		<td>Object Name</td>
 		<td><input id="objNameInput" type="text" value="" /></td>
 	</tr>
 	<tr>
 	<td><label>threshold(max number of object in violations)</label></td>
 		<td>
 			<select id="thresholdtInput" >
				<option value="50" >50</option>
				<option value="100" >100</option>
				<option value="200" >200</option>
				<option value="500" >500</option>				
			</select>
 		</td>
 		<td>Metric Name</td>
 		<td><input id="metricNameInput" type="text" value="" /></td>
 	</tr>
 	<tr>
 	<td><label>Critical violations only</label></td>
 		<td>
 			<select id="criticalityInput" >
				<option value="false" >no</option>
				<option value="true" >yes</option>
			</select>
 		</td>
 		<td>Metric ID</td>
 		<td><input id="metricIDInput" type="text" value="" /></td>
 	</tr>
 </table>
  
  <input id="Button1" type="button" onclick="valideForm()" value="Refresh" class="btn" />	
</form>
</td></tr>
</table> 
		      </div>
		    </div>
		  </div>
</body>
</html>

<script type="text/javascript">
function valideForm(){
	hfForm = $(healthFactorInput).attr("value");
	weightForm = $(weightInput).attr("value");
	thresholdtForm = $(thresholdtInput).attr("value");
	criticaltForm = $(criticalityInput).attr("value");
	moduleForm = $(moduleInput).attr("value");
	objNameForm = $(objNameInput).attr("value");
	metricNameForm = $(metricNameInput).attr("value");
	metricIDForm = $(metricIDInput).attr("value");
	
	
	document.location = "actionPlanMainResultObj.htm?schemaName="+schemaName+"&snapshotIDslashappliID="+snapshotIDslashappliID+"&hfForm="+hfForm+"&weightForm="+weightForm+"&thresholdtForm="+thresholdtForm+"&criticaltForm="+criticaltForm+"&moduleForm="+moduleForm+"&objNameForm="+objNameForm+"&metricNameForm="+metricNameForm+"&metricIDForm="+metricIDForm;
}

</script>

<!-- 
FOR
<a href='actionPlanMainResultObj.htm'></a>
 -->
