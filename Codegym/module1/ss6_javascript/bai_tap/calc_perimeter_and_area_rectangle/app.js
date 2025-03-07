function calculate() {
  let length = parseFloat(document.getElementById("length").value);
  let width = parseFloat(document.getElementById("width").value);

  let perimeter = 2 * (length + width);
  let area = length * width;

  document.getElementById("result").innerHTML =
    "Chu vi: " + perimeter + "<br>Dien tich: " + area;
}
