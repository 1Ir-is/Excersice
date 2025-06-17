<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Pig</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateForm() {
            const pidNumber = document.getElementById("pidNumber").value;
            const entryWeight = parseFloat(document.getElementById("entryWeight").value);

            if (pidNumber.length <= 10) {
                alert("ma heo phai lon 10");
                return false;
            }

            if (entryWeight <= 10) {
                alert("can nang phai lon hon 10.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Edit Pig</h1>
    <form method="post" action="/pig" class="border p-4 bg-white rounded" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${pig.id}">

        <div class="mb-3">
            <label for="pidNumber" class="form-label">PID Number:</label>
            <input type="text" id="pidNumber" name="pidNumber" class="form-control" value="${pig.pidNumber}" required>
        </div>

        <div class="mb-3">
            <label for="entryDate" class="form-label">Entry Date:</label>
            <input type="date" id="entryDate" name="entryDate" class="form-control"
                   value="${pig.entryDate != null ? pig.entryDate : ''}" required>
        </div>

        <div class="mb-3">
            <label for="entryWeight" class="form-label">Entry Weight:</label>
            <input type="number" step="0.01" id="entryWeight" name="entryWeight" class="form-control"
                   value="${pig.entryWeight}" required>
        </div>

        <div class="mb-3">
            <label for="exitDate" class="form-label">Exit Date:</label>
            <input type="date" id="exitDate" name="exitDate" class="form-control"
                   value="${pig.exitDate != null ? pig.exitDate : ''}">
        </div>

        <div class="mb-3">
            <label for="exitWeight" class="form-label">Exit Weight:</label>
            <input type="number" step="0.01" id="exitWeight" name="exitWeight" class="form-control"
                   value="${pig.exitWeight != null ? pig.exitWeight : ''}">
        </div>

        <div class="mb-3">
            <label for="originId" class="form-label">Origin:</label>
            <select id="originId" name="originId" class="form-select" required>
                <c:forEach var="origin" items="${origins}">
                    <option value="${origin.id}" <c:if test="${origin.id == pig.origin.id}">selected</c:if>>
                            ${origin.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="sold" class="form-label">Tình trạng:</label>
            <select id="sold" name="sold" class="form-select">
                <option value="false" ${!pig.sold ? 'selected' : ''}>Chưa bán</option>
                <option value="true" ${pig.sold ? 'selected' : ''}>Đã bán</option>
            </select>
        </div>


        <button type="submit" class="btn btn-primary">Update</button>
        <a href="/pig?action=list" class="btn btn-secondary">Back to List</a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>