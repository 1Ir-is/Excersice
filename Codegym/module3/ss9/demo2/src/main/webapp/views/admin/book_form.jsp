<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Book" %>
<%@ page import="models.Category" %>
<%@ page import="java.util.List" %>

<%
    Book book = (Book) request.getAttribute("book");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    boolean isEdit = book != null;
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Chỉnh sửa sách" : "Thêm sách" %></title>
</head>
<body>
<h2><%= isEdit ? "Chỉnh sửa sách" : "Thêm sách" %></h2>
<form method="post" action="books" enctype="multipart/form-data">
    <% if (isEdit) { %>
    <input type="hidden" name="ma_sach" value="<%= book.getMaSach() %>">
    <% } %>

    <label>Tên sách:</label>
    <input type="text" name="ten_sach" value="<%= isEdit ? book.getTenSach() : "" %>" required><br>

    <label>Tác giả:</label>
    <input type="text" name="tac_gia" value="<%= isEdit ? book.getTacGia() : "" %>" required><br>

    <label>NXB:</label>
    <input type="text" name="nha_xuat_ban" value="<%= isEdit ? book.getNhaXuatBan() : "" %>" required><br>

    <label>Giá:</label>
    <input type="number" name="gia" step="0.01" value="<%= isEdit ? book.getGia() : "" %>" required><br>

    <label>Mô tả:</label>
    <textarea name="mo_ta" required><%= isEdit ? book.getMoTa() : "" %></textarea><br>

    <label>Danh mục:</label>
    <select name="ma_danh_muc" required>
        <option value="">-- Chọn danh mục --</option>
        <% for (Category c : categories) { %>
        <option value="<%= c.getMaDanhMuc() %>"
                <%= isEdit && c.getMaDanhMuc() == book.getMaDanhMuc() ? "selected" : "" %>>
            <%= c.getTenDanhMuc() %>
        </option>
        <% } %>
    </select><br>

    <label>Ảnh sách:</label>
    <% if (isEdit && book.getImgUrl() != null && !book.getImgUrl().isEmpty()) { %>
    <img src="<%= book.getImgUrl() %>" alt="Ảnh sách" width="150"><br>
    <% } %>
    <input type="file" name="img_url" accept="image/*"><br>

    <button type="submit">Lưu</button>
</form>


<a href="books">⬅ Quay lại danh sách</a>
</body>
</html>
