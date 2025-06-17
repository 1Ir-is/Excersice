<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <title>Edit Course</title>
</head>
<body>
<div class="container mt-5">
  <h1 class="mb-4">Edit Course</h1>
  <form action="/course?action=update" method="post">
    <input type="hidden" name="id" value="${course.id}">
    <div class="mb-3">
      <label for="courseName" class="form-label">Course Name</label>
      <input type="text" class="form-control" id="courseName" name="courseName" value="${course.courseName}" required>
    </div>
    <div class="mb-3">
      <label for="courseLanguage" class="form-label">Language</label>
      <input type="text" class="form-control" id="courseLanguage" name="courseLanguage" value="${course.courseLanguage}" required>
    </div>
    <button type="submit" class="btn btn-success">Update</button>
    <a href="/course?action=list" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>