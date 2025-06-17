<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kết quả tra cứu</title>
</head>
<body>
<h1>Kết quả tra cứu</h1>
<c:choose>
    <c:when test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:when>
    <c:otherwise>
        <p><strong>Từ:</strong> ${word}</p>
        <p><strong>Nghĩa:</strong> ${meaning}</p>
    </c:otherwise>
</c:choose>
<a href="/">Quay lại trang chủ</a>
</body>
</html>