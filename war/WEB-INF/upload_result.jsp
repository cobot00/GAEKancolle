<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
    String msg = (String)request.getAttribute("msg");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Result</title>
</head>
<body>
    <h1>upload success</h1>
    <form action="/gaesimple/upload" method="get">
        【<%= msg %>】
        <br>
        <br>
        <input type="submit" value="return">
    </form>
</body>
</html>
