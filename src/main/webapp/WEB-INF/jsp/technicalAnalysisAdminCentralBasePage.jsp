<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2><c:out value="${NBCentral}"/> Central bases</h2>
<div class="cadreNLA">

<table border='1' class="table-striped table-bordered " align="center" > 
	<thead>
		<th><i class="icon-search"></i>Central Bases</th>
		<th>Local site (KB)</th>
		<th>CAST Version</th>
	</thead>
<c:forEach var="schema" items="${cbList.listeSchemaCb}">
	<tr>		
		<td><a href="detailCBController.htm?schemaName=<c:out value="${schema.name}"/>"><c:out value="${schema.name}"/></a></td>
		<td>
		<c:if test="${(schema.numberOfLocalSite==0)}" >
 			<span class="label label-important">Link to Local Site (kb) not set !</span>
		</c:if>
		<c:if test="${(schema.numberOfLocalSite==1)}" >
			<c:forEach var="localSite" items="${schema.localSiteList}">
				<c:out value="${localSite}"/>
			</c:forEach>
		</c:if>
		<c:if test="${(schema.numberOfLocalSite>1)}" >
			<select id="bidon" >
				<c:forEach var="localSite" items="${schema.localSiteList}">
					<option value="<c:out value="${localSite}"/>" ><c:out value="${localSite}"/></option>
				</c:forEach>
        	</select>
        </c:if>
        </td>
		<td><c:out value="${schema.version}"/></td>
	</tr>
</c:forEach>
</table>
</div>



