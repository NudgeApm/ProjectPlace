<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
var metricList = new Array();
var mMetric;
var ind=0;
var healthFactor = new Array();
healthFactor[0]="Performance";
healthFactor[1]="Robustness";
healthFactor[2]="Security";

var healthFactorID = new Array();
healthFactorID[0]="60014";
healthFactorID[1]="60013";
healthFactorID[2]="60016";


function metric(pCritical,pName,pWeight,pCheckKO,pHf) {
    this.critical=pCritical;
    this.name=pName;
    this.weight=pWeight;
    this.checkKO=pCheckKO;
    this.hf=pHf;    
}
 
<c:forEach var="metric" items="${metricList}">
	mMetric = new metric('${metric.critical}','${metric.name}','${metric.weight}','${metric.checkKO}','${metric.hfId}' );
	metricList[ind]=mMetric;
	ind = ind +1;	
</c:forEach>
</script>



<div id="divNLA">
<script type="text/javascript">
for (var j=0;j<=(healthFactorID.length-1);j=j+1){


document.write("<div class='accordion-group'>");
document.write("<div class='accordion-heading'>");
document.write("  <a class='accordion-toggle' data-toggle='collapse' data-parent=''#accordion2' href='#collapseOne"+healthFactor[j]+"'>");
document.write(healthFactor[j]);
document.write("</a>");
document.write("</div>");
document.write("<div id='collapseOne"+healthFactor[j]+"' class='accordion-body collapse'>");
document.write("  <div class='accordion-inner'>");
  
document.write("<table class='table-nla' width='900px' >");  
document.write("	<thead>");
document.write("		<tr>");
document.write("			<th>Critical</th>");
document.write("			<th>metric name</th>");
document.write("			<th>Weight</th>");
document.write("			<th># check KO</th>");
document.write("		</tr>");
document.write("	</thead>");
	for (var i=0;i<=(metricList.length-1);i=i+1){
		if(metricList[i].hf==healthFactorID[j]){
			document.write("<tr>");
			
			if(metricList[i].critical == 'true'){
				document.write("<td><i class='icon-fire'></i></td>");	
			}else{
				document.write("<td>&nbsp;</td>");
			}
			
			
			//document.write("	<td>"+metricList[i].critical+"</td>");
			document.write("	<td>"+metricList[i].name+"</td>");
			document.write("	<td>"+metricList[i].weight+"</td>");			
			document.write("	<td>"+metricList[i].checkKO+"</td>");	
			document.write("</tr>");
		}
	}	
document.write("</table>");
document.write("</div>");
		document.write("</div>");
				document.write("</div>");

}
</script>
</div>


		  