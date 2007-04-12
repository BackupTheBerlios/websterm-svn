<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/stripes.tld" prefix="stripes"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<stripes:layout-definition>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Websterm</title>
	<link rel="stylesheet" href="/websterm_client/styles/main.css"
		type="text/css" />
	<script type="text/javascript"
		src="/websterm_client/scripts/prototype/prototype.js"></script>
	<script type="text/javascript" src="/websterm_client/scripts/main.js"></script>
	<stripes:layout-component name="head" />
	</head>
	<body>
	<div id="pageHeader"><stripes:layout-component name="header">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td width="20%"
					style="border-right: 1px solid #808080; border-bottom: 1px solid #808080;"><img
					src="/websterm_client/images/websterm_logo.jpg" /></td>
				<td width="80%" valign="bottom">
				<div id="menuList">
				<ul id="navlist">
					<li id="setupMenuItem"><a href="/websterm_client/Setup.action">Setup</a></li>
					<li id="adminMenuItem"><a href="/websterm_client/Admin.action">Admin</a></li>
				</ul>
				</div>
				</td>
			</tr>
		</table>
	</stripes:layout-component></div>

	<div id="pageContent"><stripes:layout-component name="contents" /></div>

	<div id="pageFooter"><stripes:layout-component name="footer" /></div>
	</body>
	</html>
</stripes:layout-definition>
