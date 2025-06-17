<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chuyển đổi tiền tệ</title>
    <script>
        function updateLabels() {
            const conversionType = document.getElementById("conversionType").value;
            const amountLabel = document.getElementById("amountLabel");
            const exchangeRateLabel = document.getElementById("exchangeRateLabel");

            if (conversionType === "usdToVnd") {
                amountLabel.textContent = "Số tiền USD:";
                exchangeRateLabel.textContent = "Tỉ giá (VNĐ trên USD):";
            } else if (conversionType === "vndToUsd") {
                amountLabel.textContent = "Số tiền VNĐ:";
                exchangeRateLabel.textContent = "Tỉ giá (VNĐ trên USD):";
            }
        }
    </script>
</head>
<body>
<h1>Chuyển đổi tiền tệ</h1>
<p>Vui lòng nhập thông tin để thực hiện chuyển đổi:</p>
<form action="/convert" method="post">
    <label for="conversionType">Loại chuyển đổi:</label>
    <select id="conversionType" name="conversionType" required onchange="updateLabels()">
        <option value="usdToVnd">USD sang VNĐ</option>
        <option value="vndToUsd">VNĐ sang USD</option>
    </select>
    <label id="exchangeRateLabel" for="exchangeRate">Tỉ giá (VNĐ trên USD):</label>
    <input type="number" step="0.01" id="exchangeRate" name="exchangeRate" required>
    <label id="amountLabel" for="amount">Số tiền USD:</label>
    <input type="number" step="0.01" id="amount" name="amount" required>
    <button type="submit">Chuyển đổi</button>
</form>
</body>
</html>