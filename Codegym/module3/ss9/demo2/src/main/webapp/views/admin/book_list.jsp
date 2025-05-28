<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sách</title>
    <style>
        img.book-img {
            width: 80px;
            height: auto;
        }
    </style>
</head>
<body>
<h2>Danh sách sách</h2>
<a href="books?action=add">Thêm sách</a>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    Map<Integer, String> categoryMap = (Map<Integer, String>) request.getAttribute("categoryMap");
%>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Ảnh sách</th>
        <th>Tên sách</th>
        <th>Tác giả</th>
        <th>NXB</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Danh mục</th>
        <th>Hành động</th>
    </tr>
    <% for (Book book : books) { %>
    <tr>
        <td><%= book.getMaSach() %></td>
        <td>
            <% if (book.getImgUrl() != null && !book.getImgUrl().isEmpty()) { %>
            <img class="book-img" src="<%= book.getImgUrl() %>" alt="Ảnh sách">
            <% } else { %>
            <span>Chưa có ảnh</span>
            <% } %>
        </td>
        <td><%= book.getTenSach() %></td>
        <td><%= book.getTacGia() %></td>
        <td><%= book.getNhaXuatBan() %></td>
        <td><%= book.getGia() %></td>
        <td><%= book.getMoTa() %></td>
        <td><%= categoryMap.get(book.getMaDanhMuc()) %></td>
        <td>
            <a href="books?action=edit&id=<%= book.getMaSach() %>">Sửa</a> |
            <a href="books?action=delete&id=<%= book.getMaSach() %>" onclick="return confirm('Xóa sách này?')">Xóa</a>
        </td>
    </tr>
    <% } %>
</table>

<a href="<%= request.getContextPath() %>/admin/dashboard">⬅ Về Trang quản trị</a>
</body>
</html>
