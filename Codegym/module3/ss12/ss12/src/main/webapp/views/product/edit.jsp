<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sửa sản phẩm</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2>Sửa thông tin sản phẩm</h2>

  <form method="post" action="products?action=edit">
    <input type="hidden" name="id" value="${product.id}" />

    <div class="mb-3">
      <label class="form-label">Tên sản phẩm</label>
      <input type="text" class="form-control" name="name" value="${product.name}" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Giá sản phẩm</label>
      <input type="number" class="form-control" name="price" value="${product.price}" required step="0.01">
    </div>

    <div class="mb-3">
      <label class="form-label">Mô tả</label>
      <textarea class="form-control" name="description" rows="3">${product.description}</textarea>
    </div>

    <div class="mb-3">
      <label class="form-label">Nhà sản xuất</label>
      <input type="text" class="form-control" name="manufacturer" value="${product.manufacturer}" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Danh mục</label>
      <select name="categoryId" class="form-select" required>
        <option value="">-- Chọn danh mục --</option>
        <c:forEach items="${categories}" var="c">
          <option value="${c.id}" ${c.id == product.categoryId ? 'selected' : ''}>${c.name}</option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn btn-primary">Cập nhật</button>
    <a href="products" class="btn btn-secondary">Hủy</a>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
