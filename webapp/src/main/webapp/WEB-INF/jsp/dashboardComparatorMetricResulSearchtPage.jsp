<ul class="nav nav-tabs">
  <li><a href="#menu0" data-toggle="tab">-</a></li>
  <li><a href="#menu1" data-toggle="tab">Search filters</a></li>
  <li><a href="#menu2" data-toggle="tab">Summary</a></li>
  <li><a href="#menu3" data-toggle="tab">Details</a></li>
</ul>
	<div class="tab-content">
	<div class="tab-pane active" id="menu0">&nbsp;</div>
  	<div class="tab-pane" id="menu1">
  		<table width="100%">
				<tr><td>
					<form class="well" id="testForm" action="time.htm" >
 					<table border="0">
 						<tr>
					 		<td><label>Health factor</label></td>
					 		<td>  	
					 			<select id="healthFactorInput" >
									<option value="Transferability" >Transferability</option>
									<option value="Changeability" >Changeability</option>
									<option value="Robustness" >Robustness</option>
									<option value="Performance" selected >Performance</option>
									<option value="Security" >Security</option>
								</select>
 							</td>
 							<td><label>Grade Between:</label></td>
 							<td><input id="gradeMinInput" type="text"  value="0"  /></td>
 							<td><label>and</label></td>
 							<td><input id="gradeMaxInput" type="text" value="4" /></td>
 						</tr>
 						<tr>
					 		<td><label>Metric name</label></td>
 							<td><input id="metricNameInput" type="text" value="" /></td>
 							<td><label># Violations Between:</label></td>
 							<td><input id="violMinInput" type="text" value="0" /></td>
 							<td><label>and</label></td>
 							<td><input id="violMaxInput" type="text" value="10000" /></td>
 						</tr>
 						<tr>
 							<td><label>Technical Criteria</label></td>
 							<td><input id="TCNameInput" type="text" value="" /></td>
 							<td><label>Weight Between:</label></td>
 							<td><input id="weightMinInput" type="text" value="0" /></td>
 							<td><label>and</label></td>
 							<td><input id="weightMaxInput" type="text" value="22" /></td>
 						</tr>
 					</table>
					<input id="Button1" type="button" value="Refresh" class="btn" />	
					</form>
				</td></tr>
			</table> 
	</div>
  <div class="tab-pane" id="menu2">
  
  <table>
		<tr>
			<td>Application: ${appName}</td>
		</tr>
		<tr>
			<td>Snapshot: ${snapshotName}</td>
		</tr>
		<tr>
			<td>Central Base: ${schemaName}</td>
		</tr>		
	</table>
	</div>
  <div class="tab-pane" id="menu3">.3..</div>
