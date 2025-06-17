<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pig List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Quản lý heo</h1>

    <!-- Search Form -->
    <form method="get" action="/pig" class="mb-4">
        <input type="hidden" name="action" value="list">
        <div class="row g-3 align-items-end">

            <div class="col-md-3">
                <label for="status" class="form-label">Tình trạng heo</label>
                <select id="status" name="status" class="form-select">
                    <option value="">Tất cả</option>
                    <option value="sold">Đã bán</option>
                    <option value="unsold">Chưa bán</option>
                </select>
            </div>


            <div class="col-md-3">
                <label for="origin" class="form-label">Xuất xứ</label>
                <select id="origin" name="originId" class="form-select">
                    <option value="">Tất cả</option>
                    <c:forEach var="o" items="${origins}">
                        <option value="${o.id}" ${o.id == selectedOriginId ? 'selected' : ''}>${o.name}</option>
                    </c:forEach>
                </select>
            </div>



            <div class="col-md-3">
                <label for="pidNumber" class="form-label">Mã số heo</label>
                <input type="text" id="pidNumber" name="pidNumber" class="form-control" placeholder="Nhập mã heo" value="${searchedPidNumber}">
            </div>


            <div class="col-md-3 d-grid">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </div>
        </div>
    </form>


    <!-- Pig Table -->
    <table class="table table-bordered table-striped">
        <thead class="table-warning">
        <tr>
            <th>ID</th>
            <th>Mã số heo</th>
            <th>Ngày nhập chuồng</th>
            <th>Trọng lượng nhập</th>
            <th>Ngày xuất chuồng</th>
            <th>Trọng lượng xuất</th>
            <th>Xuất xứ</th>
            <th>Tình trạng</th>
            <th>Chi tiết</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty pigs}">
            <tr>
                <td colspan="9" class="text-center">Danh sách trống</td>
            </tr>
        </c:if>
        <c:forEach var="pig" items="${pigs}">
            <tr>
                <td>${pig.id}</td>
                <td>${pig.pidNumber}</td>
                <td>${pig.entryDate}</td>
                <td>${pig.entryWeight}</td>
                <td>${pig.exitDate != null ? pig.exitDate : 'Chưa đến ngày xuất'}</td>
                <td>${pig.exitWeight != null ? pig.exitWeight : '-'}</td>
                <td>${pig.origin.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${pig.sold}"><span class="badge bg-success">Đã bán</span></c:when>
                        <c:otherwise><span class="badge bg-secondary">Chưa bán</span></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="/pig?action=view&id=${pig.id}" class="btn btn-info btn-sm">Xem</a>
                    <a href="/pig?action=edit&id=${pig.id}" class="btn btn-warning btn-sm">Sửa</a>
                    <button class="btn btn-danger btn-sm" onclick="confirmDelete(${pig.id}, '${pig.pidNumber}')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/pig?action=add" class="btn btn-success">Thêm heo mới</a>
</div>

<!-- Modal xác nhận xóa -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form method="post" action="/pig">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" id="deletePigId" name="id">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                </div>
                <div class="modal-body">
                    Bạn có muốn xóa heo có mã số <strong id="pigPidNumber"></strong>?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger">Xóa</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Không</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDelete(id, pidNumber) {
        document.getElementById('deletePigId').value = id;
        document.getElementById('pigPidNumber').innerText = pidNumber;
        new bootstrap.Modal(document.getElementById('deleteModal')).show();
    }
</script>
</body>
</html>
