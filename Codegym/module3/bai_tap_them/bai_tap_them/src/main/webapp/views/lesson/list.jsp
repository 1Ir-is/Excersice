<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Lesson List</title>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Lesson List</h1>
    <div class="d-flex justify-content-between mb-3">
        <form class="d-flex" action="/lesson" method="get">
            <input type="hidden" name="action" value="list">
            <input type="text" name="search" class="form-control me-2" placeholder="Search by lesson name">
            <select name="courseId" class="form-select me-2">
                <option value="">All Courses</option>
                <c:forEach var="course" items="${courses}">
                    <option value="${course.id}">${course.courseName}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        <div>
            <a href="/lesson?action=add" class="btn btn-success">Add New Lesson</a>
            <a href="/course?action=add" class="btn btn-secondary">Add New Course</a>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Lesson Code</th>
            <th>Lesson Name</th>
            <th>Type</th>
            <th>Course Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty lessons}">
            <tr>
                <td colspan="6" class="text-center">No data available</td>
            </tr>
        </c:if>
        <c:forEach var="lesson" items="${lessons}">
            <tr>
                <td>${lesson.id}</td>
                <td>${lesson.lessonCode}</td>
                <td>${lesson.lessonName}</td>

                <td>${lesson.lessonType}</td>
                <td>${lesson.courseName}</td>

                <td>
                    <a href="/lesson?action=view&id=${lesson.id}" class="btn btn-info btn-sm">View</a>
                    <a href="/lesson?action=edit&id=${lesson.id}" class="btn btn-warning btn-sm">Edit</a>
                    <button
                            type="button"
                            class="btn btn-danger btn-sm"
                            data-bs-toggle="modal"
                            data-bs-target="#deleteModal"
                            data-id="${lesson.id}"
                            data-name="${fn:escapeXml(lesson.lessonName)}">
                        Delete
                    </button>




                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc muốn xóa bài học này không?
            </div>
            <div class="modal-footer">
                <form id="deleteForm" method="post" action="/lesson?action=delete">
                    <input type="hidden" name="id" id="deleteLessonId">
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </form>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const deleteModal = document.getElementById('deleteModal');

        deleteModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;

            const lessonId = button.getAttribute('data-id');
            const lessonName = button.getAttribute('data-name');

            document.getElementById('deleteLessonId').value = lessonId;

            const modalBody = deleteModal.querySelector('.modal-body');
            modalBody.innerHTML = lessonName
                ? `Bạn có chắc muốn xóa bài học <strong>${lessonName}</strong> không?`
                : "Không tìm thấy tên bài học.";

            console.log(`🗑️ Modal triggered for lesson ID: ${lessonId}, Name: ${lessonName}`);
        });
    });
</script>




</body>
</html>