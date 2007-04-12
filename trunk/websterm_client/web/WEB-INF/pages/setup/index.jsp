<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<stripes:layout-render name="/WEB-INF/pages/layout/setup.jsp">
	<stripes:layout-component name="mainContents">
		<div id="uploadRulesDiv"><stripes:form action="/Setup.action">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td style="text-align: center"><stripes:file
						name="ruleDefinitionFile" /></td>
					<td><stripes:submit name="uploadRuleDefinition" value="Upload" /></td>
				</tr>
			</table>
		</stripes:form></div>
		<div id="configDiv">
		<div id="configTitle" class="blockTitle"><span>Other
		Configurations</span></div>
		<div id="configContent" class="blockBody">
		<div id="configSubtitle" class="blockSubtitle">Type of Input
		Data</div>
		<div id="typeOfInputDiv"><stripes:form
			action="/Configuration.action">
			<stripes:checkbox name="database" id="database"
				onchange="javascript:getDbConfigDetails()"></stripes:checkbox>Database
			<stripes:checkbox name="file" id="file"
				onchange="javascript:getFileConfigDetails()"></stripes:checkbox>File
		</stripes:form></div>
		<div id="messageArea"></div>
		<div id="dbConfigs"></div>
		<div id="fileConfigs"></div>
		</div>
		</div>
	</stripes:layout-component>
</stripes:layout-render>
