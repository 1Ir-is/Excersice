<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<h1>Simple Calculator</h1>
<form action="/calculate" method="get">
    <label>Number 1:</label>
    <input type="text" name="num1" required><br>
    <label>Number 2:</label>
    <input type="text" name="num2" required><br>
    <label>Operation:</label>
    <select name="operation">
        <option value="add">Add</option>
        <option value="subtract">Subtract</option>
        <option value="multiply">Multiply</option>
        <option value="divide">Divide</option>
    </select><br>
    <button type="submit">Calculate</button>
</form>
</body>
</html>