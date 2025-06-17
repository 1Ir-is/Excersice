<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Kết quả chuyển đổi</title>
</head>
<body>
<h1>Kết quả chuyển đổi</h1>
<p>Tỉ giá: ${exchangeRate} VNĐ/USD</p>
<c:choose>
  <c:when test="${conversionType == 'usdToVnd'}">
    <p>Số tiền USD: ${usdAmount} USD</p>
    <p>Số tiền sau chuyển đổi: ${vndAmount} VNĐ</p>
  </c:when>
  <c:when test="${conversionType == 'vndToUsd'}">
    <p>Số tiền VNĐ: ${vndAmount} VNĐ</p>
    <p>Số tiền sau chuyển đổi: ${usdAmount} USD</p>
  </c:when>
</c:choose>
<a href="/">Quay lại trang chủ</a>
</body>
</html>