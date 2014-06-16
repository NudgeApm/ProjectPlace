<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
var metricList = new Array();
var maMetric;
var ind=0;
var maxWeight=<c:out value="${maxMetricWeight}"/>;
var maxTCWeight=<c:out value="${maxTCWeight}"/>;
var schemaName='<c:out value="${schemaName}"/>';
var snapshotIDslashappliID='<c:out value="${snapshotIDslashappliID}"/>';

function metric(pCritical,pName,pWeight,pGrade,pCheckOK,pCheckKO,pTotal,pTc_name,pTc_weight,pTc_grade,pHf,pId,pAf,pnbAction) {
    this.critical=pCritical;
    this.name=pName;
    this.weight=pWeight;
    this.grade=pGrade;
    this.checkOK=pCheckOK;
    this.checkKO=pCheckKO;
    this.total=pTotal;
    this.tc_name=pTc_name;
    this.tc_weight=pTc_weight;
    this.tc_grade=Math.round(pTc_grade*100)/100;
    this.hf=pHf;    
    this.weightPercent = Math.round(pWeight/maxWeight * 100);
    this.tcWeightPercent = Math.round(pTc_weight/maxTCWeight * 100);
    this.id=pId;
    this.af=pAf;
    this.nbAction=pnbAction;
}
 
<c:forEach var="metric" items="${metricList}">
	maMetric = new metric('${metric.critical}','${metric.nameHTML}','${metric.weight}','${metric.grade}','${metric.checkOK}','${metric.checkKO}','${metric.total}','${metric.technicalCriteria}','${metric.tcWeight}','${metric.tcGrade}','${metric.healthFactor}','${metric.id}','${metric.affiche}','${metric.nbAction}');
	metricList[ind]=maMetric;
	ind = ind +1;	
</c:forEach>

$(document).ready(function() {
	$('#Button1').click(function() {    	
		checkOK="";
		var gradeMinInputForm = $('#gradeMinInput').attr("value");
		var gradeMaxInputForm = $('#gradeMaxInput').attr("value");

		if(!(isNaN(gradeMinInputForm)) && !(isNaN(gradeMaxInputForm)) ){
			if(gradeMaxInputForm <= 4 && gradeMinInputForm >= 0){
				checkOK="";
			}else{
				checkOK="grade must be between 0 and 4";
			}
			if(gradeMaxInputForm <gradeMinInputForm){
				checkOK="Grade min must be lower than grade max";
			}
		}else{
			checkOK="you must put an integer value for grade.";
		}
		
				
		var violMinInputForm = $('#violMinInput').attr("value");
		var violMaxInputForm = $('#violMaxInput').attr("value");

		if(!(isNaN(violMinInputForm)) && !(isNaN(violMaxInputForm)) ){
			if(violMaxInputForm <violMinInputForm){
				checkOK="# violations min must be lower than # violations max";
			}
		}else{
			checkOK="you must put an integer value for violation (#).";
		}		
		if(checkOK != ""){
			alert(checkOK);
			return;
		}

		var weightMinInputForm = $('#weightMinInput').attr("value");
		var weightMaxInputForm = $('#weightMaxInput').attr("value");

		if(!(isNaN(weightMinInputForm)) && !(isNaN(weightMaxInputForm)) ){
			if(parseFloat(weightMaxInputForm) <parseFloat(weightMinInputForm)){
				checkOK="weight min must be lower than weight max";
			}
		}else{
			checkOK="you must put an integer value for weight.";
		}		
		if(checkOK != ""){
			alert(checkOK);
			return;
		}
		
		
		
		hfForm = $('#healthFactorInput').attr("value");
		metricNameForm = $('#metricNameInput').attr("value");
		TCNameInputForm = $('#TCNameInput').attr("value");
		
		for (var i=0;i<=(metricList.length-1);i=i+1){
			if(metricList[i].hf == hfForm){
				metricList[i].af=1;
			}else{
				metricList[i].af=0;
			}
			var nom = metricList[i].name;			
			if(nom != "" && nom.toUpperCase().indexOf(metricNameForm.toUpperCase()) == -1){				
				metricList[i].af=0;
			}
			var TCnom = metricList[i].tc_name;
			if(TCnom != "" && TCnom.toUpperCase().indexOf(TCNameInputForm.toUpperCase()) == -1){				
				metricList[i].af=0;
			}
			var grade = parseFloat(metricList[i].grade);
			if(grade > parseFloat(gradeMaxInputForm) ){
				metricList[i].af=0;
			}
			if(grade < parseFloat(gradeMinInputForm) ){
				metricList[i].af=0;
			}
			var violNB = parseFloat(metricList[i].checkKO);
			if(violNB > parseFloat(violMaxInputForm)){
				metricList[i].af=0;
			}
			if(violNB < parseFloat(violMinInputForm)){
				metricList[i].af=0;
			}
			var weightNB = parseFloat(metricList[i].weight);
			if(weightNB > parseFloat(weightMaxInputForm)){
				metricList[i].af=0;
			}
			if(weightNB < parseFloat(weightMinInputForm)){
				metricList[i].af=0;
			}
			
		}
		var contenu = "";
    	
    	
    	
    	
    	contenu = contenu + "<table class='table-striped table-bordered' >";  
    	contenu = contenu + "<thead>";
    	contenu = contenu + "<tr>";
    	contenu = contenu + "<th>Weight</th>";
    	contenu = contenu + "<th>Grade</th>";
    	contenu = contenu + "<th>Technical Criterion</th>";
    	contenu = contenu + "<th>Criticality</th>";    			
    	contenu = contenu + "<th>Weight</th>";
    	contenu = contenu + "<th>Grade</th>";
    	contenu = contenu + "<th nowrap>ID</th>";
    	contenu = contenu + "<th>Name</th>";    	
    	contenu = contenu + "<th nowrap># Violations</th>";
    	contenu = contenu + "<th nowrap># OK</th>";
    	contenu = contenu + "<th># Obj in Action Plan</th>";
    	//contenu = contenu + "<th>Total</th>";
    	//contenu = contenu + "<th>Health factor</th>";
    	contenu = contenu + "</tr>";
    	contenu = contenu + "</thead>";    	

    	for (var i=0;i<=(metricList.length-1);i=i+1){
    		if(metricList[i].af == 1){
    			contenu = contenu + "<tr>";
    			contenu = contenu + "<td><div class='progress'><div class='bar' style='width: "+metricList[i].tcWeightPercent+"%;''>"+metricList[i].tc_weight+"</div></div></td>";
    			contenu = contenu + "<td><button class='btn ";
    			if(metricList[i].tc_grade == 4){
    				contenu = contenu + "btn-success";
    			}
    			if(metricList[i].tc_grade < 4 && metricList[i].tc_grade > 2){
    				contenu = contenu + "btn-warning";
    			}
    			if(metricList[i].tc_grade < 2 && metricList[i].tc_grade >= 1){
    				contenu = contenu + "btn-danger";
    			}		
    			contenu = contenu + " btn-mini disabled'>"+metricList[i].tc_grade+"</button></td>";
    			contenu = contenu + "<td nowrap>"+metricList[i].tc_name+"</td>";
    			contenu = contenu + "<td>";
    			if(metricList[i].critical == 'true'){
    				contenu = contenu + "<i class='icon-asterisk'></i>";	
    			}
    			contenu = contenu + "</td>";
    			contenu = contenu + "<td><div class='progress' style='vertical-align: middle;' ><div class='bar' style='width: "+metricList[i].weightPercent+"%;''>"+metricList[i].weight+"</div></div></td>";
    			contenu = contenu + "<td><button class='btn ";
    			if(metricList[i].grade == 4){
    				contenu = contenu + "btn-success";
    			}
    			if(metricList[i].grade < 4 && metricList[i].grade > 2){
    				contenu = contenu + "btn-warning";
    			}
    			if(metricList[i].grade < 2 && metricList[i].grade >= 1){
    				contenu = contenu + "btn-danger";
    			}		
    			contenu = contenu + " btn-mini disabled'>"+metricList[i].grade+"</button></td>";
    			contenu = contenu + "<td>"+metricList[i].id+"</td>";
    			
    			contenu = contenu + "<td nowrap><a href='actionPlanDetailMetric.htm?schemaName="+schemaName+"&metricID="+metricList[i].id+"&snapshotIDslashappliID="+snapshotIDslashappliID+"'>"+metricList[i].name+"</a></td>";
    			//contenu = contenu + "<td>"+metricList[i].name+"</td>";
				
				contenu = contenu + "<td>";
    			if(metricList[i].checkKO == 0){
    				contenu = contenu + "<i class='icon-minus'></i>";
    			}else{
    				contenu = contenu + metricList[i].checkKO;
    			}		
    			contenu = contenu + "</td>";
    			contenu = contenu + "<td>"+metricList[i].checkOK+"</td>";
    			if(metricList[i].nbAction == 0){	
    				contenu = contenu + "<td nowrap>&nbsp;</td>";
    			}else{
    				contenu = contenu + "<td nowrap>"+metricList[i].nbAction+"</td>";
    			}

    			//contenu = contenu + "<td>"+metricList[i].total+"</td>";    			
    			contenu = contenu + "</tr>";
    		}
    	}
    	contenu = contenu + "</table>";
    	
    	$('#divNLA').html(contenu);
    	
    	
    	
    });
});   

$("form.testForm").submit(function() {
	s = $(this).serialize();
	$.ajax({
			type: "POST",
	 		//data: s,
	 		data: {
	 			snapshotIDslashappliID: '5,3', // Les donnees que l'on souhaite envoyer au serveur au format JSON
	 			schemaName: 'ap_demo_object_list'
  			},
	 		url: $(this).attr("action"),
	 		error:function(msg){
	 		     alert( "Error !: " + msg );
	 		   },
	 		success: function(retour){
				//$("#testAllerRetour").empty().append(retour);
				//$("#testAllerRetour").html(data);
	 			$("#testAllerRetour").text("test ok");
	 			alert("retour ok");
	 		}
	 	});
	return false;
	 });


    function doAjax() {
		alert("ll");
      $.ajax({
        url: 'actionPlanMainResult2.html',
        data: ({name : "me"}),
        success: function(data) {
          $('#time').html(data);
          alert("ok");
        }
      });
    }
    
  </script>

<div id="divNLA" class="cadreNLA">
<table class="table-striped table-bordered " >  
	<thead>
		<tr>
			
			
			<th>Weight</th>
			<th>Grade</th>
			<th>Technical Criterion</th>
			<th>Criticality</th>
			
			<!-- th>ID</th-->
			<th>Weight</th>
			<th>Grade</th>
			<th>ID</th>
			<th>Name</th>
			<th nowrap># Violations</th>
			<th># OK</th>
			<th># Obj in Action Plan</th>
			<!-- >th>Total</th-->
			
			<!-- >th>Health factor</th-->
		</tr>
	</thead>
	
	<script type="text/javascript">
	for (var i=0;i<=(metricList.length-1);i=i+1){
		if(metricList[i].af == 1){
			document.write("<tr>");
			
			document.write("<td><div class='progress'><div class='bar' style='width: "+metricList[i].tcWeightPercent+"%;''>"+metricList[i].tc_weight+"</div></div></td>");
			document.write("<td><button class='btn ");
			if(metricList[i].tc_grade == 4){
				document.write(" btn-success");
			}
			if(metricList[i].tc_grade < 4 && metricList[i].tc_grade > 2){
				document.write(" btn-warning");
			}
			if(metricList[i].tc_grade < 2 && metricList[i].tc_grade >= 1){
				document.write(" btn-danger");
			}		
			document.write(" btn-mini disabled'>"+metricList[i].tc_grade+"</button></td>");
			document.write("<td nowrap>"+metricList[i].tc_name+"</td>");
			document.write("<td>");			
			if(metricList[i].critical == 'true'){
				document.write("<i class='icon-asterisk'></i>");	
			}
			document.write("</td>");
			document.write("<td style='vertical-align: middle'><div style='vertical-align: middle' class='progress' ><div class='bar' style='vertical-align: middle; width: "+metricList[i].weightPercent+"%;''>"+metricList[i].weight+"</div></div></td>");
			document.write("<td><button class='btn ");
			if(metricList[i].grade == 4){
				document.write("btn-success");
			}
			if(metricList[i].grade < 4 && metricList[i].grade > 2){
				document.write("btn-warning");
			}
			if(metricList[i].grade < 2 && metricList[i].grade >= 1){
				document.write("btn-danger");
			}		
			document.write(" btn-mini disabled'>"+metricList[i].grade+"</button></td>");
			document.write("<td nowrap>"+metricList[i].id+"</td>");
			document.write("<td nowrap><a href='actionPlanDetailMetric.htm?schemaName="+schemaName+"&metricID="+metricList[i].id+"&snapshotIDslashappliID="+snapshotIDslashappliID+"'>"+metricList[i].name+"</a></td>");
			
			document.write("<td>");
			if(metricList[i].checkKO == 0){
				document.write("<i class='icon-minus'></i>");
			}else{
				document.write(metricList[i].checkKO);	
			}				
			document.write("</td>");
			document.write("<td>");
			if(metricList[i].checkOK == 0){
				document.write("<i class='icon-minus'></i>");
			}else{
				document.write(metricList[i].checkOK);
			}		
			document.write("</td>");
		//	document.write("<td>"+metricList[i].total+"</td>");
			if(metricList[i].nbAction == 0){	
				document.write("<td nowrap>&nbsp;</td>");
			}else{
				document.write("<td nowrap>"+metricList[i].nbAction+"</td>");
			}
			//document.write("<td>"+metricList[i].hf+"</td>");
			document.write("</tr>");
		}
	}
	</script>
</table>
</div>