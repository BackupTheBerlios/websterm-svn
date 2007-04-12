<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<thead>
		<tr>
			<th>Selected?</th>
			<th>Table</th>
			<th>Column</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${actionBean.fields}" var="field" varStatus="loop">
			<tr>
				<td width="10%"><input type="checkbox"
					name="databaseConfiguration.fields[${loop.index}].selected" /></td>
				<td width="45%"><c:out value="${field.table}"></c:out></td>
				<td width="45%"><c:out value="${field.column}"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
