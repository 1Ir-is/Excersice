<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>

<form action="/products/${product.id}" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${product.name}" required>
    <br>
    <label for="price">Price:</label>
    <input type="number" id="price" name="price" step="0.01" value="${product.price}" required>
    <br>
    <button type="submit">Update</button>
</form>

<a href="/products">Back to List</a>
</body>
</html>
