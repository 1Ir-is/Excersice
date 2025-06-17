<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Pig</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">View Pig</h1>
    <div class="border p-4 bg-white rounded">
        <p><strong>ID:</strong> ${pig.id}</p>
        <p><strong>PID Number:</strong> ${pig.pidNumber}</p>
        <p><strong>Entry Date:</strong> ${pig.entryDate}</p>
        <p><strong>Entry Weight:</strong> ${pig.entryWeight}</p>
        <p><strong>Exit Date:</strong> ${pig.exitDate != null ? pig.exitDate : 'Chưa đến ngày xuất'}</p>
        <p><strong>Exit Weight:</strong> ${pig.exitWeight}</p>
        <p><strong>Origin:</strong> ${pig.origin.name}</p>
        <p><strong>Tình trạng:</strong>
            <c:choose>
                <c:when test="${pig.sold}">Đã bán</c:when>
                <c:otherwise>Chưa bán</c:otherwise>
            </c:choose>
        </p>

        <a href="/pig?action=list" class="btn btn-secondary">Back to List</a>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>