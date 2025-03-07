function calculateAverage() {
  let physicScore = parseFloat(document.getElementById("physic").value);
  let chemistryScore = parseFloat(document.getElementById("chemistry").value);
  let biologyScore = parseFloat(document.getElementById("biology").value);
  if (
    isNaN(chemistryScore) ||
    isNaN(physicScore) ||
    isNaN(biologyScore) ||
    physicScore <= 0 ||
    chemistryScore <= 0 ||
    biologyScore <= 0
  ) {
    alert("Vui long nhap so lieu hop le");
    return;
  }
  let sumScore = physicScore + chemistryScore + biologyScore;
  let averageScore = parseInt(sumScore / 3);

  document.getElementById("result").innerHTML =
    "Diem tong: " + sumScore + "<br><br>Diem trung binh: " + averageScore;
}
