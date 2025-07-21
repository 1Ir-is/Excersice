// const person = {
//   firstName: "John",
//   lastName: "Doe",
//   age: 30,
//   gender: "Male",
//   occupation: "Software Engineer",
//   nationality: "American",
//   city: "New York",
//   hobbies: ["reading", "traveling", "gaming"],
//   languages: ["English", "Spanish"],
//   education: {
//     degree: "Bachelor",
//     major: "Computer Science",
//     university: "MIT",
//   },
// };

// // destructuring de lay thuoc tinh
// const {
//   firstName,
//   gender,
//   education: { degree },
//   languages: [english],
//   ...restStudent
// } = person;

// // doi tuong student
// const student = {
//   firstName,
//   gender,
//   degree,
//   english,
// };

// console.log(student);

const array = [10, 15, 20, 30, 35];
const sumEven = array
  .filter((num) => num % 2 === 0)
  .reduce((sum, num) => sum + num, 0);
console.log("Tổng các phần tử chẵn:", sumEven);
