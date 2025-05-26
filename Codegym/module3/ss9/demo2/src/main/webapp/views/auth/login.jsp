<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/26/2025
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Đăng nhập</h2>
<form method="post" action="<%= request.getContextPath() %>/login">
    Email: <input type="email" name="email" required><br><br>
    Mật khẩu: <input type="password" name="password" required><br><br>

    <label>
        <input type="checkbox" name="remember"> Ghi nhớ đăng nhập
    </label><br><br>

    <button type="submit">Đăng nhập</button>
</form>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red"><%= error %></p>
<%
    }
%>

<p>Chưa có tài khoản? <a href="<%= request.getContextPath() %>/register">Đăng ký ngay</a></p>
</body>
</html>


