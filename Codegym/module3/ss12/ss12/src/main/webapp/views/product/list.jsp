<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Danh sách sản phẩm</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">

  <h2 class="mb-4">Danh sách sản phẩm</h2>

  <!-- Form tìm kiếm -->
  <form method="get" action="products" class="row mb-4">
    <input type="hidden" name="action" value="" />
    <div class="col-md-4">
      <input type="text" name="name" value="${name}" class="form-control" placeholder="Tên sản phẩm">
    </div>
    <div class="col-md-4">
      <select name="categoryId" class="form-select">
        <option value="0">Tất cả danh mục</option>
        <c:forEach items="${categories}" var="c">
          <option value="${c.id}" ${c.id == categoryId ? 'selected' : ''}>${c.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="col-md-4">
      <button class="btn btn-primary" type="submit">Tìm kiếm</button>
      <a href="products?action=create" class="btn btn-success">Thêm sản phẩm</a>
      <a href="${pageContext.request.contextPath}/categories?action=create" class="btn btn-secondary">Thêm danh mục</a>
    </div>

  </form>

  <!-- Bảng danh sách -->
  <table class="table table-bordered table-striped">
    <thead>
    <tr>
      <th>#</th>
      <th>Tên</th>
      <th>Giá</th>
      <th>Nhà sản xuất</th>
      <th>Danh mục</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.manufacturer}</td>
        <td>
          <c:forEach var="cat" items="${categories}">
            <c:if test="${cat.id == product.categoryId}">${cat.name}</c:if>
          </c:forEach>
        </td>
        <td>
          <a href="products?action=detail&id=${product.id}" class="btn btn-info btn-sm">Chi tiết</a>
          <a href="products?action=edit&id=${product.id}" class="btn btn-warning btn-sm">Sửa</a>
          <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal${product.id}">Xoá</button>

          <!-- Modal xác nhận xoá -->
          <div class="modal fade" id="deleteModal${product.id}" tabindex="-1" aria-labelledby="deleteLabel${product.id}" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="deleteLabel${product.id}">Xác nhận xoá</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                  Bạn có chắc chắn muốn xoá sản phẩm "<strong>${product.name}</strong>"?
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                  <a href="products?action=delete&id=${product.id}" class="btn btn-danger">Xoá</a>
                </div>
              </div>
            </div>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Phân trang -->
  <nav>
    <ul class="pagination">
      <c:forEach var="i" begin="1" end="${totalPages}">
        <li class="page-item ${i == currentPage ? 'active' : ''}">
          <a class="page-link" href="products?page=${i}&name=${name}&categoryId=${categoryId}">${i}</a>
        </li>
      </c:forEach>
    </ul>
  </nav>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
