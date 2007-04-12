<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<stripes:layout-definition>
	<stripes:layout-render name="/WEB-INF/pages/layout/main.jsp">
		<stripes:layout-component name="contents">
			<table cellpadding="0" cellspacing="0" width="100%" border="0">
				<tr>
					<td width="20%"
						style="border-right: 1px solid #808080; padding-top: 10px;"
						align="right" valign="top">
					<div id="sidemenucontainer">
					<ul id="sidemenu">
						<li><stripes:link
							beanclass="org.jaden.websterm.client.action.AdminAction"
							event="toggleEngine">Start/Stop Engine</stripes:link></li>
						<li><stripes:link
							beanclass="org.jaden.websterm.client.action.AdminAction"
							event="viewLogs">View Logs</stripes:link></li>
						<li><stripes:link
							beanclass="org.jaden.websterm.client.action.AdminAction"
							event="viewResults">View Results</stripes:link></li>
					</ul>
					</div>
					</td>
					<td width="80%" class="mainContents">${mainContents}</td>
				</tr>
			</table>
		</stripes:layout-component>
	</stripes:layout-render>
</stripes:layout-definition>
