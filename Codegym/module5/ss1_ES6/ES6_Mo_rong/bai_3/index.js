function getInfo(obj) {
  const { firstName = "Quan", degree = "NA" } = obj;
  console.log("firsName: " + firstName);
  console.log("degree: " + degree);
}

const sv1 = {
  firstName: "John",
  gender: "male",
  degree: "Bachelor",
  english: "English",
};
getInfo(sv1);

const sv2 = {
  name: "Jane",
  gender: "female",
  degree: "Master",
  english: "English",
};
getInfo(sv2);
