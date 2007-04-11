<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<c:import url="/WEB-INF/config/dialects.xml" var="xml" />
<x:parse doc="${xml}" var="dialects" scope="application" />
<c:if test="${actionBean.database}">
	<div id="databaseConfigSection">
	<div class="blockSubTitle">Database Configuration</div>
	<div class="blockContents"><stripes:form
		action="/Configuration.action">
				Type of Database: <stripes:select
			name="databaseConfiguration.databaseType">
			<x:forEach select="$applicationScope:dialects/dialects/*"
				var="dialect">
				<c:set var="class">
					<x:out select="$dialect/class" />
				</c:set>
				<stripes:option value="${class}">
					<x:out select="$dialect/vendor" />
				</stripes:option>
			</x:forEach>
		</stripes:select>
		<br />
		JDBC Connection URL: <stripes:text
			name="databaseConfiguration.connectionUrl"></stripes:text>
		<br />
		Fields to be checked:<br />
	</stripes:form></div>
	</div>
</c:if>
<c:if test="${actionBean.file}">
	<div id="fileConfigSection">
	<div class="blockSubTitle">File Configuration</div>
	<div class="blockContents"><stripes:form
		action="/Configuration.action">
				File directories:
	</stripes:form></div>
</c:if>
