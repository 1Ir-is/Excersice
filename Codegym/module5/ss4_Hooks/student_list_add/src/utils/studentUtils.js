export function filterStudents(students, searchTerm) {
  return students.filter(
    (sinhVien) =>
      sinhVien.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      (sinhVien.code &&
        sinhVien.code.toLowerCase().includes(searchTerm.toLowerCase()))
  );
}
