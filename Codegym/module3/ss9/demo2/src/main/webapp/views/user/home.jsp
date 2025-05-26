<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/26/2025
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User" %>
<%
  User user = (User) session.getAttribute("user");
%>
<html>
<head><title>Home</title></head>
<body>
<h2>Trang chủ</h2>

<%
  if (user != null) {
%>
<p>Chào mừng, <%= user.getTen() %>!</p>
<a href="<%= request.getContextPath() %>/logout">Đăng xuất</a>
<%
} else {
%>
<p>Bạn chưa đăng nhập.</p>
<a href="<%= request.getContextPath() %>/login">Đăng nhập</a> |
<a href="<%= request.getContextPath() %>/register">Đăng ký</a>
<%
  }
%>

</body>
</html>


