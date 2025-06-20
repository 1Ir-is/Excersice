<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/18/2025
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Product</th>
        <th>Action</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href="/products/${product.id}">View</a>
                <a href="/products/edit/${product.id}">Edit</a>
                <form action="/products/delete/${product.id}" method="post">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/products/new">Add Product</a>
</body>
</html>
