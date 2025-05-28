<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/28/2025
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Category" %>
<%
  List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý Danh mục</title>
</head>
<body>
<h2>📚 Danh sách Danh mục</h2>
<a href="categories?action=add">➕ Thêm danh mục</a>
<table border="1">
  <tr>
    <th>Mã danh mục</th>
    <th>Tên danh mục</th>
    <th>Hành động</th>
  </tr>
  <% for (Category c : categories) { %>
  <tr>
    <td><%= c.getMaDanhMuc() %></td>
    <td><%= c.getTenDanhMuc() %></td>
    <td>
      <a href="categories?action=edit&id=<%= c.getMaDanhMuc() %>">Sửa</a>
      |
      <a href="categories?action=delete&id=<%= c.getMaDanhMuc() %>" onclick="return confirm('Xóa danh mục này?')">Xóa</a>
    </td>
  </tr>
  <% } %>
</table>
<a href="<%= request.getContextPath() %>/admin/dashboard">⬅ Về Trang quản trị</a>
</body>
</html>

