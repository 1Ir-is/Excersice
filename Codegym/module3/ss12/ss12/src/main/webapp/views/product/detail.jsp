<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Chi tiết sản phẩm</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2>Chi tiết sản phẩm</h2>

  <table class="table table-bordered">
    <tr>
      <th>ID</th>
      <td>${product.id}</td>
    </tr>
    <tr>
      <th>Tên sản phẩm</th>
      <td>${product.name}</td>
    </tr>
    <tr>
      <th>Giá</th>
      <td>${product.price}</td>
    </tr>
    <tr>
      <th>Mô tả</th>
      <td>${product.description}</td>
    </tr>
    <tr>
      <th>Nhà sản xuất</th>
      <td>${product.manufacturer}</td>
    </tr>
    <tr>
      <th>Danh mục</th>
      <td>
        <c:forEach items="${categories}" var="c">
          <c:if test="${c.id == product.categoryId}">
            ${c.name}
          </c:if>
        </c:forEach>
      </td>
    </tr>
  </table>

  <a href="products" class="btn btn-primary">Quay lại danh sách</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
