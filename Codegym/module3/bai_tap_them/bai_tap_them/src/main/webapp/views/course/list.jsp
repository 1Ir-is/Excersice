<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Course List</title>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Course List</h1>
    <a href="/course?action=add" class="btn btn-primary mb-3">Add New Course</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Course Name</th>
            <th>Language</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.id}</td>
                <td>${course.courseName}</td>
                <td>${course.courseLanguage}</td>
                <td>
                    <a href="/course?action=view&id=${course.id}" class="btn btn-info btn-sm">View</a>
                    <a href="/course?action=edit&id=${course.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="/course?action=delete&id=${course.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>