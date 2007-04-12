<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<c:if test="${actionBean.file}">
	<div id="fileConfigSection">
	<div class="blockSubTitle">File Configuration</div>
	<div class="blockContents"><stripes:form
		action="/Configuration.action">
				File directories:
	</stripes:form></div>
</c:if>
