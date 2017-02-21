<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="Generate.do">
	<select name="selectedRule">
		<c:forEach items="${allRules}" var="rule">
			<option value="${rule.code}">${rule.code}</option>
		</c:forEach>
	</select>
	<select name="language">
		<option value="plsql">PLSQL</option>
		<option value="java">Java</option>
	</select>
	<button type="submit">Generate Single Rule</button>
</form>
<div style="height: 1px; width: 100%; background-color: black; padding: 0px 2px 0px 2px;"></div>
<form action="Generate.do">
	<select name="language">
		<option value="plsql">PLSQL</option>
		<option value="java">Java</option>
	</select>
	<button type="submit">Generate All</button>
</form>


</body>
</html>