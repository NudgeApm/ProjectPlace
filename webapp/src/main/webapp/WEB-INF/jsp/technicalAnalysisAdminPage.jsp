<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Technology', '# applications'],         
          <c:forEach var="techno" items="${listTechnologies}">      			
      		['<c:out value="${techno.name}"/>',<c:out value="${techno.applicationCount}"/> ],
	      </c:forEach>         
          ['X',    0]
        ]);

        var dataVersion = google.visualization.arrayToDataTable([
           ['Version', '# applications'],         
           <c:forEach var="version" items="${listVersion}">      			
           ['<c:out value="${version.s}"/>',<c:out value="${version.count}"/> ],
 	      </c:forEach>         
           ['X',    0]
         ]);

        var options = {
          title: '# Applications / Technologies',
          is3D: true,
          backgroundColor: '#666666',
          legend:{position: 'top', textStyle: {color: 'black', fontSize: 8}},
          chartArea:{left:5,top:55,width:"100%",height:"100%"},
        };

        var optionsVersion = {
                title: '# Applications / Version',
                is3D: true, 
                backgroundColor: '#669966',
                legend:{position: 'top', textStyle: {color: 'black', fontSize: 8}},
                chartArea:{left:5,top:55,width:"100%",height:"100%"},
              };
        
        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
        
        var chartVersion = new google.visualization.PieChart(document.getElementById('piechart_3dVersion'));
        chartVersion.draw(dataVersion, optionsVersion);
      }
    </script>
  </head>
  
<table width="600" border="0">
	<tr>
		<td colspan="2" width="200" height="100" >
		  <div class="jumbotron">
  		  <h4><a href="technicalAnalysisAdminApp.htm"> <c:out value="${NBapplications}"/></a><br> Applications</h4>
		  </div>
		</td width="200">
		<td colspan="2" >
		  <div class="jumbotronGreen">
		  <h4><a href="technicalAnalysisAdminMngtBase.htm"> <c:out value="${NBManagementBase}"/></a><br> Management bases</h4>  
		  </div>
		</td>
		<td colspan="2" width="200">
		  <div class="jumbotronGreen">
		  <h4><a href="technicalAnalysisAdminCentralBase.htm"> <c:out value="${NBCentral}"/></a><br> Central bases</h4>
		  </div>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<div id="piechart_3d" style="width: 300px; height: 180px;"></div>
		</td>
		<td colspan="3">
			<div id="piechart_3dVersion" style="width: 300px; height: 180px;"></div>
		</td>
	</tr>
</table>
