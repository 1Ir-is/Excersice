<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Course Details</title>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Course Details</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Course Name: ${course.courseName}</h5>
            <p class="card-text">Language: ${course.courseLanguage}</p>
            <a href="/course?action=list" class="btn btn-primary">Back to List</a>
        </div>
    </div>
</div>
</body>
</html>