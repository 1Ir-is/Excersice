function checkDivide() {
  const a = parseFloat(document.getElementById("a").value);
  const b = parseFloat(document.getElementById("b").value);
  let result;

  if (b === 0) {
    result = "b cannot be zero.";
  } else if (a % b === 0) {
    result = `${a} divides ${b}.`;
  } else {
    result = `${a} does not divide ${b}.`;
  }

  document.getElementById("result").innerText = result;
}
