function calculateArea() {
  const side = parseFloat(document.getElementById("side").value);
  let result;

  if (isNaN(side) || side <= 0) {
    result = "Please enter a valid positive number for the side length.";
  } else {
    const area = side * side;
    result = `The area of the square with side length ${side} is ${area}.`;
  }

  document.getElementById("result").innerText = result;
}
