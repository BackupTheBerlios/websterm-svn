<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<stripes:layout-definition>
	<stripes:layout-render name="/WEB-INF/pages/layout/main.jsp">
		<stripes:layout-component name="contents">
			<table cellpadding="0" cellspacing="0" width="100%" border="0">
				<tr>
					<td width="20%" style="border-right: 1px solid gray;">
					<ul>
						<li><a href="/websterm_client/Setup.action">Configuration</a></li>
						<li><stripes:link
							beanclass="org.jaden.websterm.client.action.SetupAction"
							event="viewRules">View Rules</stripes:link></li>
					</ul>
					</td>
					<td width="80%" class="mainContents">${mainContents}</td>
				</tr>
			</table>
		</stripes:layout-component>
	</stripes:layout-render>
</stripes:layout-definition>
