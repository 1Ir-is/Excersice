export let studentList = [
  {
    id: 1,
    name: "Huynh Minh Huy",
    age: 20,
    email: "huy@gmail.com",
    gender: "Nam",
    courses: ["ReactJS", "NodeJS"],
  },
  {
    id: 2,
    name: "Nguyen Duc Vinh",
    age: 20,
    email: "vinh@gmail.com",
    gender: "Nam",
    courses: ["Java"],
  },
];

export function getAll() {
  return studentList;
}

export function addNew(student) {
  student.id = Date.now();
  studentList.push(student);
}

export function deleteById(id) {
  studentList = studentList.filter((student) => student.id !== id);
}

export function getById(id) {
  return studentList.find((student) => student.id === id);
}

export function updateById(id, newStudent) {
  studentList = studentList.map((student) =>
    student.id === id ? { ...student, ...newStudent } : student
  );
}
