function calculatePerimeter(radius) {
  const perimeter = 2 * Math.PI * radius;
  return perimeter;
}

function displayPerimeter() {
  const radius = parseFloat(document.getElementById("radius").value);
  const perimeter = calculatePerimeter(radius);
  document.getElementById("result").innerText = `Perimeter: ${perimeter.toFixed(
    2
  )}`;
}
