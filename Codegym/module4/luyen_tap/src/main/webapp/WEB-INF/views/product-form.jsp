<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<h1>Add Product</h1>
<form action="/products" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
    <br>
    <label for="price">Price:</label>
    <input type="number" id="price" name="price" required>
    <br>
    <button type="submit">Add Product</button>
</form>
<a href="/products">Back to List</a>
</body>
</html>