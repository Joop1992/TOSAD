<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import= "java.util.HashMap, java.util.Map, java.util.Iterator, java.util.Set" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="InsertTrigger.do">
	<button type="submit">Insert All</button>
</form>
<a href="Execute.do" style="float: right;">Go back to selector</a>

<% HashMap hmap = (HashMap)request.getSession().getAttribute("rulesHmap"); 
   Set set = hmap.entrySet();
   Iterator iterator = set.iterator();
   while(iterator.hasNext()) {
       Map.Entry mentry = (Map.Entry)iterator.next();
%>
   	   <textarea style="padding: 5px 5px 5px 5px; display: block; height: 300px; width: 1000px;" disabled><%=mentry.getValue()%></textarea>
       <a href="InsertTrigger.do?stringId=<%=mentry.getKey()%>">Insert This Single Rule</a>
<%
   }

%>
</body>
</html>