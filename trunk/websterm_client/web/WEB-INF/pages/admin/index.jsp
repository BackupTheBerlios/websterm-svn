<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<stripes:layout-render name="/WEB-INF/pages/layout/admin.jsp">
	<stripes:layout-component name="mainContents">
		<div id="controlPanel">
		<button id="startEngineButton"
			onclick="javascript:toggleEngine('Start');">Start Engine</button>
		<button id="stopEngineButton"
			onclick="javascript:toggleEngine('Stop');">Stop Engine</button>
		</div>
		<div id="statusPanel"></div>
	</stripes:layout-component>
</stripes:layout-render>
