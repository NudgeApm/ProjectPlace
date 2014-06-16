<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2><c:out value="${NBManagementBase}"/>  Management bases</h2>
<div class="cadreNLA">
<table border='1' class="table-striped table-bordered " align="center" > 
	<thead>
		<th>Management Bases</th>
		<th>Central base</th>
		<th>Knowledge base</th>
		<th>Server</th>
		<th>(#)Applications</th>
	</thead>
<c:forEach var="schema" items="${mngtList.listeSchemaMngt}">
	<tr>		
		<td><c:out value="${schema.name}"/></td>
		<td>
		<c:if test="${(schema.numberOfCentralBase==0)}" >
 			<span class="label label-important">Link to central base not set!</span>
		</c:if>
		<c:if test="${(schema.numberOfCentralBase==1)}" >
			<c:forEach var="centralBase" items="${schema.listCB}">
				<c:out value="${centralBase}"/>
			</c:forEach>
		</c:if>
		<c:if test="${(schema.numberOfCentralBase>1)}" >
			<select id="bidon" >
				<c:forEach var="centralBase" items="${schema.listCB}">
					<option value="<c:out value="${centralBase}"/>" ><c:out value="${centralBase}"/></option>
				</c:forEach>
        	</select>
        </c:if>
        </td>
		<td>
		<c:if test="${(schema.numberOfKnowledgeBase==0)}" >
 			<span class="label label-important">Link to knowledge base not set!</span>
		</c:if>
		<c:if test="${(schema.numberOfKnowledgeBase==1)}" >
			<c:forEach var="kb" items="${schema.listKB}">
				<c:out value="${kb}"/>
			</c:forEach>
		</c:if>
		<c:if test="${(schema.numberOfKnowledgeBase>1)}" >
			<select id="bidon" >
				<c:forEach var="kb" items="${schema.listKB}">
					<option value="<c:out value="${kb}"/>" ><c:out value="${kb}"/></option>
				</c:forEach>
        	</select>
        </c:if>
        </td>
       <td>${schema.connectionName}</td>
        <td>
          <div class="accordion-group">
		    <div class="accordion-heading">
		      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne${schema.name}">
		        ${schema.numberOfApplications}
		      </a>
		    </div>
		    <div id="collapseOne${schema.name}" class="accordion-body collapse">
		      <div class="accordion-inner">
		        ${schema.applicationsName}
		      </div>
		    </div>
		  </div>
        
        </td>
        
	</tr>
</c:forEach>  
</table>	
</div>




