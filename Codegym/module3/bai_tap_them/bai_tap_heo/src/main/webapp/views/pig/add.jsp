<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Pig</title>
</head>
<body>
<h1>Add Pig</h1>
<form method="post" action="/pig">
    <input type="hidden" name="action" value="add">
    <label for="pidNumber">PID Number:</label>
    <input type="text" id="pidNumber" name="pidNumber">
    <br>
    <label for="entryDate">Entry Date:</label>
    <input type="date" id="entryDate" name="entryDate">
    <br>
    <label for="entryWeight">Entry Weight:</label>
    <input type="number" id="entryWeight" name="entryWeight">
    <br>
    <label for="exitDate">Exit Date:</label>
    <input type="date" id="exitDate" name="exitDate">
    <br>
    <label for="exitWeight">Exit Weight:</label>
    <input type="number" id="exitWeight" name="exitWeight">
    <br>
    <label for="originId">Origin:</label>
    <input type="number" id="originId" name="originId">
    <br>
    <label for="sold">Sold:</label>
    <input type="checkbox" id="sold" name="sold">
    <br>
    <button type="submit">Add</button>
</form>
<a href="/pig?action=list">Back to List</a>
</body>
</html>