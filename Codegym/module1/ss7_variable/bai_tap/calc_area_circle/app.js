function calculateArea() {
  let radius = parseInt(document.getElementById("radius").value);
  let area = Math.PI * Math.pow(radius, 2);
  document.getElementById("result").innerText = "Area: " + area;
}
