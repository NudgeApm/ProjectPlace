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

function metric(pCritical,pName,pWeight,pGrade,pCheckOK,pCheckKO,pTotal,pTc_name,pTc_weight,pTc_grade,pHf,pId,pAf,pnbAction,pWeight2,pCheckKO2,pCheckOK2,pGrade2,pName2) {
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
    this.weight2=pWeight2;
    this.checkKO2=pCheckKO2;
    this.checkOK2=pCheckOK2;
    this.grade2=pGrade2;
    this.name2=pName2;
    this.gradeDiff=roundNumber(pGrade2-pGrade,2);
    this.checkKODiff=roundNumber(pCheckKO2-pCheckKO,2);
    this.checkOKDiff=roundNumber(pCheckOK2-pCheckOK,2);
    this.gradeDiffPercent=roundNumber((this.gradeDiff / pGrade)*100,2);
    this.checkOKDiffPercent=0;
    if(this.checkOK != 0){
    	this.checkOKDiffPercent=roundNumber((this.checkOKDiff / pCheckOK)*100,2);	
    }    
    this.checkKODiffPercent=0;
    if(this.checkKO != 0){
    	this.checkKODiffPercent=roundNumber((this.checkKODiff / pCheckKO)*100,2);	
    }else{
    	if(this.checkKO2 == 0 ){
    		this.checkKODiffPercent="Nan";
    	}else{
    		this.checkKODiffPercent=100;	
    	}
    		
    }
    
}
 
<c:forEach var="metric" items="${metricList}">
	maMetric = new metric('${metric.critical}','${metric.nameHTML}','${metric.weight}','${metric.grade}','${metric.checkOK}','${metric.checkKO}','${metric.total}','${metric.technicalCriteria}','${metric.tcWeight}','${metric.tcGrade}','${metric.healthFactor}','${metric.id}','${metric.affiche}','${metric.nbAction}','${metric.weight2}','${metric.checkKO2}','${metric.checkOK2}','${metric.grade2}','${metric.nameHTML2}');
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
    	contenu = contenu + "<th nowrap># Violations 2</th>";
    	contenu = contenu + "<th nowrap># OK</th>";
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
				contenu = contenu + "<td>";
    			if(metricList[i].checkKO2 == 0){
    				contenu = contenu + "<i class='icon-minus'></i>";
    			}else{
    				contenu = contenu + metricList[i].checkKO2;
    			}		
    			contenu = contenu + "</td>";
    			contenu = contenu + "<td>"+metricList[i].checkOK+"</td>";
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
    
    function roundNumber(rnum, rlength) { // Arguments: number to round, number of decimal places
    	  var newnumber = Math.round(rnum*Math.pow(10,rlength))/Math.pow(10,rlength);
    	  return parseFloat(newnumber); // Output the result to the form field (change for your purposes)
    	}
    
  </script>

<div id="divNLA" class="cadreNLA">
<table class="table-striped table-bordered " >  
	<thead>
		<tr>			
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			<th colspan="3" >${schemaName}</th>
			<th colspan="3" nowrap>${schemaNameComp}</th>
			<th colspan="3" nowrap>Difference</th>
			<th colspan="3" nowrap>Diff. Percentage</th>
		</tr>
	</thead>
	<thead>
		<tr>			
			<th>ID</th>
			<th>Name</th>
			<th >Grade</th>
			<th nowrap># Violations</th>
			<th ># OK</th>
			<th >Grade</th>
			<th nowrap># Violations</th>
			<th ># OK</th>
			<th >Grade</th>
			<th nowrap># Violations</th>
			<th ># OK</th>
			<th >Grade</th>
			<th nowrap># Violations</th>
			<th ># OK</th>
			
		</tr>
	</thead>
	<script type="text/javascript">
	for (var i=0;i<=(metricList.length-1);i=i+1){
		if(metricList[i].af == 1){
			document.write("<tr>");
			
			
			
			document.write("<td nowrap>"+metricList[i].id+"</td>");
			if(metricList[i].name == ""){
				document.write("<td nowrap>"+metricList[i].name2+"</td>");
			}else{
				document.write("<td nowrap>"+metricList[i].name+"</td>");
			}
			
			
			if(metricList[i].grade > 0){
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
			
				document.write("<td>");
				if(metricList[i].checkKO == 0){
					document.write("<i class='icon-remove-sign'></i>");
				}else{
					document.write(metricList[i].checkKO);	
				}				
				document.write("</td>");
				document.write("<td>");
				if(metricList[i].checkOK == 0){
					document.write("<i class='icon-remove-sign'></i>");
				}else{
					document.write(metricList[i].checkOK);
				}		
				document.write("</td>");
			}else{
				document.write("<td colspan='3'>&nbsp;</td>");
			}
			if(metricList[i].grade2 > 0){
				document.write("<td><button class='btn ");
				if(metricList[i].grade2 == 4){
					document.write("btn-success");
				}
				if(metricList[i].grade2 < 4 && metricList[i].grade2 > 2){
					document.write("btn-warning");
				}
				if(metricList[i].grade2 < 2 && metricList[i].grade2 >= 1){
					document.write("btn-danger");
				}		
				document.write(" btn-mini disabled'>"+metricList[i].grade2+"</button></td>");
				
				
				
				document.write("<td>");
				if(metricList[i].checkKO2 == 0){
					document.write("<i class='icon-remove-sign'></i>");
				}else{
					document.write(metricList[i].checkKO2);	
				}				
				document.write("</td>");
				
				document.write("<td>");
				if(metricList[i].checkOK2 == 0){
					document.write("<i class='icon-remove-sign'></i>");
				}else{
					document.write(metricList[i].checkOK2);
				}		
				document.write("</td>");
			}else{
				document.write("<td colspan='3'>&nbsp;</td>");
			}
			if(metricList[i].grade2 > 0 && metricList[i].grade > 0){
				document.write("<td >"+metricList[i].gradeDiff+"</td>");
				document.write("<td >"+metricList[i].checkKODiff+"</td>");
				document.write("<td >"+metricList[i].checkOKDiff+"</td>");
			}else{
				document.write("<td colspan='3'>&nbsp;</td>");
			}
			if(metricList[i].grade2 > 0 && metricList[i].grade > 0){
				document.write("<td >"+metricList[i].gradeDiffPercent+" %</td>");
				document.write("<td >"+metricList[i].checkKODiffPercent+" %</td>");
				document.write("<td >"+metricList[i].checkOKDiffPercent+" %</td>");
			}else{
				document.write("<td colspan='3'>&nbsp;</td>");
			}
			document.write("</tr>");
		}
	}
	</script>
</table>
</div>