<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<stripes:layout-render name="/WEB-INF/pages/layout/main.jsp">
	<stripes:layout-component name="contents">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr align="center">
				<td align="left">${actionBean.fileContent}</td>
			</tr>
		</table>
	</stripes:layout-component>
</stripes:layout-render>
