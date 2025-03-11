function solveEquation() {
  const a = parseFloat(document.getElementById("coefficientA").value);
  const b = parseFloat(document.getElementById("coefficientB").value);
  let result;

  if (isNaN(a) || isNaN(b)) {
    result = "Please enter valid numbers for the coefficients.";
  } else if (a === 0 && b === 0) {
    result = "The equation has infinitely many solutions.";
  } else if (a === 0) {
    result = "The equation has no solution.";
  } else {
    const x = -b / a;
    result = `The solution to the equation ${a}x + ${b} = 0 is x = ${x}.`;
  }

  document.getElementById("result").innerText = result;
}
