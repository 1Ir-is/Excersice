export function filteredStudents(student, search) {
  if (!search) {
    return student;
  }

  return student.filter(
    (s) =>
      s.name.toLowerCase().includes(search.toLowerCase()) ||
      s.email.toLowerCase().includes(search.toLowerCase())
  );
}
