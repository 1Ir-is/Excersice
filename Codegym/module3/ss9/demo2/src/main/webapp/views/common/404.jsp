<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/28/2025
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 - Không tìm thấy trang</title>
</head>
<body>
<h2>🚫 404 - Trang không tồn tại</h2>
<p>Rất tiếc! Đường dẫn bạn yêu cầu không tồn tại hoặc đã bị xóa.</p>
<a href="<%= request.getContextPath() %>/">Quay về trang chủ</a>
</body>
</html>

