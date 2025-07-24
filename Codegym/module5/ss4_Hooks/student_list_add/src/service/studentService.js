export let studentList = [
  {
    name: "Huynh Minh Huy",
    age: 20,
    email: "huy@gmail.com",
  },
  {
    name: "Nguyen Van A",
    age: 20,
    email: "a@gmail.com",
  },
];

export function getAll() {
  return studentList;
}

export function addNew(student) {
  studentList.push(student);
}

export function deleteById(id) {
  studentList = studentList.filter((s) => s.id !== id);
}
