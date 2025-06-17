<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>View Lesson</title>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Lesson Details</h1>
    <p><strong>ID:</strong> ${lesson.id}</p>
    <p><strong>Lesson Code:</strong> ${lesson.lessonCode}</p>
    <p><strong>Lesson Name:</strong> ${lesson.lessonName}</p>
    <p><strong>Type:</strong> ${lesson.lessonType}</p>
    <p><strong>Description:</strong> ${lesson.lessonDescription}</p>
    <p><strong>Course ID:</strong> ${lesson.courseId}</p>
    <a href="/lesson?action=list" class="btn btn-secondary">Back to List</a>
</div>
</body>
</html>