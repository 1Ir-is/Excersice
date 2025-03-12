function solveQuadraticEquation() {
  const a = parseFloat(document.getElementById("coefficientA").value);
  const b = parseFloat(document.getElementById("coefficientB").value);
  const c = parseFloat(document.getElementById("coefficientC").value);
  let result;

  if (isNaN(a) || isNaN(b) || isNaN(c)) {
    result = "Please enter valid numbers for the coefficients.";
  } else if (a === 0) {
    result = "This is not a quadratic equation.";
  } else {
    const discriminant = b * b - 4 * a * c;
    if (discriminant > 0) {
      const x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
      const x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
      result = `The equation has two distinct real roots: x1 = ${x1} and x2 = ${x2}.`;
    } else if (discriminant === 0) {
      const x = -b / (2 * a);
      result = `The equation has one real root: x = ${x}.`;
    } else {
      result = "The equation has no real roots.";
    }
  }

  document.getElementById("result").innerText = result;
}
