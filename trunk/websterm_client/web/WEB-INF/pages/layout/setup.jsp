<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<stripes:layout-definition>
	<stripes:layout-render name="/WEB-INF/pages/layout/main.jsp">
		<stripes:layout-component name="contents">
			<table cellpadding="0" cellspacing="0" width="100%" border="0">
				<tr>
					<td width="30%">
					<ul>
						<li><a href="javascript://nop/">Configuration</a></li>
						<li><a href="javascript://nop/">View Rules</a></li>
					</ul>
					</td>
					<td width="70%"><stripes:layout-component name="mainContents" /></td>
				</tr>
			</table>
		</stripes:layout-component>
	</stripes:layout-render>
</stripes:layout-definition>
