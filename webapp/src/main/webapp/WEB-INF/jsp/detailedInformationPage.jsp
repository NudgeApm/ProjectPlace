<div class="accordion-group">
	<div class="accordion-heading">
		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOneSummary">
			<i class="icon-search"></i>Info	
		</a>
	</div>
	<div id="collapseOneSummary" class="accordion-body collapse">
	<div class="accordion-inner">
	<ul class="nav nav-tabs">
  <li><a href="#home" data-toggle="tab">Summary</a></li>
  <li><a href="#profile" data-toggle="tab">Action Plan</a></li>
  <li><a href="#messages" data-toggle="tab">Messages</a></li>
  <li><a href="#settings" data-toggle="tab">Settings</a></li>
</ul>
<div class="tab-content">
  <div class="tab-pane active" id="home">
  <table>
		<tr><td>Application: ${appName}</td></tr>
		<tr><td>Snapshot: ${snapshotName}</td></tr>
		<tr><td>Central Base: ${schemaName}</td></tr>		
	</table>
	</div>
  <div class="tab-pane" id="profile"> 
  
 <form class='well' id='theFormAll' action='#' >
	<table>
		<tr>
			<td><label>Priority</label></td>
			<td>  	<select id='priorityInputAll' >
						<option value='4' >Low</option>
						<option value='3' >Moderate</option>
						<option value='2' >High</option>
						<option value='1' selected >Extreme</option>
					</select>
			</td>
		</tr>
		<tr>
			<td><label>Comment</label></td>
			<td><input id='commentInputAll' type='text' value='' /></td>
		</tr>
	</table>
 	<input id='Button1' type='button' onclick='valideFormMultipleObj()' value='Add to Action plan' class='btn' />
</form>  
  
  </div>
  <div class="tab-pane" id="messages">.3..</div>
  <div class="tab-pane" id="settings">.4..</div>
</div>	
	</div>
	</div>
</div>
