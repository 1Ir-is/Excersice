function checkTriangle() {
  const sideA = parseFloat(document.getElementById("sideA").value);
  const sideB = parseFloat(document.getElementById("sideB").value);
  const sideC = parseFloat(document.getElementById("sideC").value);
  let result;

  if (
    isNaN(sideA) ||
    isNaN(sideB) ||
    isNaN(sideC) ||
    sideA <= 0 ||
    sideB <= 0 ||
    sideC <= 0
  ) {
    result = "Please enter valid positive numbers for all sides.";
  } else if (
    sideA + sideB > sideC &&
    sideB + sideC > sideA &&
    sideA + sideC > sideB
  ) {
    result = `The sides ${sideA}, ${sideB}, and ${sideC} can form a triangle.`;
  } else {
    result = `The sides ${sideA}, ${sideB}, and ${sideC} cannot form a triangle.`;
  }

  document.getElementById("result").innerText = result;
}
