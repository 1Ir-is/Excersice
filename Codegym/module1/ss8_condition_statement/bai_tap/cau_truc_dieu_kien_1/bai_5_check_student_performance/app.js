function checkPerformance() {
  const testScore = parseFloat(document.getElementById("testScore").value);
  const midtermScore = parseFloat(
    document.getElementById("midtermScore").value
  );
  const finalScore = parseFloat(document.getElementById("finalScore").value);

  const averageScore = (testScore + midtermScore + finalScore) / 3;
  let performance;

  if (averageScore >= 9) {
    performance = "Excellent";
  } else if (averageScore >= 7) {
    performance = "Good";
  } else if (averageScore >= 5) {
    performance = "Average";
  } else {
    performance = "Poor";
  }

  document.getElementById(
    "result"
  ).innerText = `The student's performance is ${performance}.`;
}
