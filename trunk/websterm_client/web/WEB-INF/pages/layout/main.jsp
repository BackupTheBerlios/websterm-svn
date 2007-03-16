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
	<stripes:layout-component name="head" />
	</head>
	<body>
	<stripes:layout-component name="header">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td width="30%">j</td>
				<td width="70%">j</td>
			</tr>
		</table>
	</stripes:layout-component>

	<div id="pageContent"><stripes:layout-component name="contents" /></div>

	<stripes:layout-component name="footer">

	</stripes:layout-component>
	</body>
	</html>
</stripes:layout-definition>
