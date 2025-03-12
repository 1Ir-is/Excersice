function calculateArea() {
  const sideA = parseFloat(document.getElementById("sideA").value);
  const sideB = parseFloat(document.getElementById("sideB").value);
  let result;

  if (isNaN(sideA) || sideA <= 0 || isNaN(sideB) || sideB <= 0) {
    result = "Please enter valid positive numbers for the side lengths.";
  } else {
    const area = sideA * sideB;
    result = `The area of the rectangle with side lengths ${sideA} and ${sideB} is ${area}.`;
  }

  document.getElementById("result").innerText = result;
}
