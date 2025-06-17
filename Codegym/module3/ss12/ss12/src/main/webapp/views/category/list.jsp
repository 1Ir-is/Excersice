<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Quản lý Danh mục</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-4">
  <h2>Danh sách Danh mục</h2>
  <a href="${pageContext.request.contextPath}/categories?action=create" class="btn btn-primary mb-3">Thêm danh mục mới</a>
  <a href="${pageContext.request.contextPath}/products" class="btn btn-info text-white">Quay về sản phẩm</a>

  <table class="table table-bordered table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Tên danh mục</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
      <tr>
        <td>${category.id}</td>
        <td>${category.name}</td>
        <td>
          <a href="${pageContext.request.contextPath}/categories?action=edit&id=${category.id}" class="btn btn-warning btn-sm">Sửa</a>

          <!-- Xóa dùng modal bootstrap -->
          <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal${category.id}">
            Xóa
          </button>

          <div class="modal fade" id="deleteModal${category.id}" tabindex="-1" aria-labelledby="deleteModalLabel${category.id}" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="deleteModalLabel${category.id}">Xác nhận xóa</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  Bạn có chắc muốn xóa danh mục <strong>${category.name}</strong> không?
                </div>
                <div class="modal-footer">
                  <form method="post" action="${pageContext.request.contextPath}/categories?action=delete&id=${category.id}">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-danger">Xóa</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
