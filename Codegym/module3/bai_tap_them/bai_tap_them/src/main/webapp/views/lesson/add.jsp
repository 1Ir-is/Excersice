<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Add Lesson</title>
    <script>
        function validateForm() {
            const lessonCode = document.getElementById("lessonCode").value;
            const lessonDescription = document.getElementById("lessonDescription").value;

            const lessonCodePattern = /^LESSON-[A-Z0-9]{4}$/;
            if (!lessonCodePattern.test(lessonCode)) {
                alert("Sai format");
                return false;
            }

            if (lessonDescription.length < 50) {
                alert("phai toi da 50 ky tu");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Add New Lesson</h1>
    <form action="/lesson?action=add" method="post" onsubmit="return validateForm()">
        <div class="mb-3">
            <label for="lessonCode" class="form-label">Lesson Code</label>
            <input type="text" class="form-control" id="lessonCode" name="lessonCode" pattern="LESSON-[A-Z0-9]{4}" required>
        </div>
        <div class="mb-3">
            <label for="lessonName" class="form-label">Lesson Name</label>
            <input type="text" class="form-control" id="lessonName" name="lessonName" required>
        </div>
        <div class="mb-3">
            <label for="lessonType" class="form-label">Lesson Type</label>
            <select class="form-select" id="lessonType" name="lessonType" required>
                <option value="Nghe">Nghe</option>
                <option value="Đọc">Đọc</option>
                <option value="Nói">Nói</option>
                <option value="Viết">Viết</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="lessonDescription" class="form-label">Description</label>
            <textarea class="form-control" id="lessonDescription" name="lessonDescription" rows="3" minlength="50" required></textarea>
        </div>
        <div class="mb-3">
            <label for="courseId" class="form-label">Course</label>
            <select class="form-select" id="courseId" name="courseId" required>
                <c:forEach var="course" items="${courses}">
                    <option value="${course.id}">${course.courseName}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
        <a href="/lesson?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>