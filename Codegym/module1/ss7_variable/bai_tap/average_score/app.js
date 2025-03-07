function calculateAverage() {
  let physicScore = parseFloat(document.getElementById("physic").value);
  let chemistryScore = parseFloat(document.getElementById("chemistry").value);
  let biologyScore = parseFloat(document.getElementById("biology").value);

  let sumScore = physicScore + chemistryScore + biologyScore;
  let averageScore = sumScore / 3;

  document.getElementById("result").innerHTML =
    "Diem tong: " + sumScore + "<br>Diem trung binh: " + averageScore;
}
