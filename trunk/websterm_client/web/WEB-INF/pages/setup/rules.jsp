<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<stripes:layout-render name="/WEB-INF/pages/layout/setup.jsp">
	<stripes:layout-component name="mainContents">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead>
				<tr align="center">
					<th width="10%">Rules Name</th>
					<th width="10%">Priority</th>
					<th>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<th colspan="3">Functions</th>
						</tr>
						<tr>
							<th width="20"%>Name</th>
							<th width="60%">Parameters</th>
							<th width="20%">Reverse?</th>
						</tr>
					</table>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${actionBean.rules}" var="rule">
					<tr>
						<td>${rule.name}</td>
						<td>${rule.priority}</td>
						<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<c:forEach items="${rule.functions}" var="function">
								<tr>
									<td width="20%">${function.name}</td>
									<td width="60%"><c:forEach items="${function.params}" var="p">
									${p}&nbsp;</c:forEach></td>
									<td width="20%">${function.reverse}</td>
								</tr>
							</c:forEach>
						</table>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</stripes:layout-component>
</stripes:layout-render>
