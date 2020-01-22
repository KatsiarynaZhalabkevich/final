<%@page import="by.epam.web.unit6.bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>hello, user</h1>
<% User user;
user = (User)request.getAttribute("user");
out.print(user.getName());%>
<%=user.getRole() %>
<!--при нажатии кнопки мы перейдем на контроллер и вызовем команду edit_profile-->
<form action="controller" method ="get">
<input type="hidden" name="command" value="edit_profile"/>
<input type="submit" value="press me"/>
</form>
</body>
</html>
