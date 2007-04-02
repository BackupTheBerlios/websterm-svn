<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<stripes:layout-render name="/WEB-INF/pages/layout/setup.jsp">
	<stripes:layout-component name="mainContents">
		<stripes:form action="/Setup.action">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td style="text-align: center"><stripes:file
						name="ruleDefinitionFile" /></td>
				</tr>
				<tr>
					<td><stripes:submit name="uploadRuleDefinition" value="Upload" /></td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
