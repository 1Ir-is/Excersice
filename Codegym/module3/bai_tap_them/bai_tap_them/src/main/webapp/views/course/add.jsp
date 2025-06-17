<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <title>Add Course</title>
</head>
<body>
<div class="container mt-5">
  <h1 class="mb-4">Add New Course</h1>
  <form action="/course?action=add" method="post">
    <div class="mb-3">
      <label for="courseName" class="form-label">Course Name</label>
      <input type="text" class="form-control" id="courseName" name="courseName" required>
    </div>
    <div class="mb-3">
      <label for="courseLanguage" class="form-label">Language</label>
      <input type="text" class="form-control" id="courseLanguage" name="courseLanguage" required>
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
    <a href="/course?action=list" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>