<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Edit Lesson</title>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Edit Lesson</h1>
    <form action="/lesson?action=update" method="post">
        <input type="hidden" name="id" value="${lesson.id}">
        <div class="mb-3">
            <label for="lessonCode" class="form-label">Lesson Code</label>
            <input type="text" class="form-control" id="lessonCode" name="lessonCode" value="${lesson.lessonCode}" required>
        </div>
        <div class="mb-3">
            <label for="lessonName" class="form-label">Lesson Name</label>
            <input type="text" class="form-control" id="lessonName" name="lessonName" value="${lesson.lessonName}" required>
        </div>
        <div class="mb-3">
            <label for="lessonType" class="form-label">Lesson Type</label>
            <input type="text" class="form-control" id="lessonType" name="lessonType" value="${lesson.lessonType}" required>
        </div>
        <div class="mb-3">
            <label for="lessonDescription" class="form-label">Description</label>
            <textarea class="form-control" id="lessonDescription" name="lessonDescription" rows="3" required>${lesson.lessonDescription}</textarea>
        </div>
        <div class="mb-3">
            <label for="courseId" class="form-label">Course ID</label>
            <input type="number" class="form-control" id="courseId" name="courseId" value="${lesson.courseId}" required>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="/lesson?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>