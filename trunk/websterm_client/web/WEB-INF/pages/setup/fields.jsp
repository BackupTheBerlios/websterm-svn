<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<c:forEach items="${actionBean.fields}" var="field">
	<c:out value="${field}"></c:out>
</c:forEach>
