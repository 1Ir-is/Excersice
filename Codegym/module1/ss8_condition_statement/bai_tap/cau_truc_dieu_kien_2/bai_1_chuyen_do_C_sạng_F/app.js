function convertToFahrenheit() {
  let celsius = parseFloat(document.getElementById("celsius").value);
  let result;

  if (isNaN(celsius)) {
    result = "Please enter a valid number.";
  } else {
    let fahrenheit = (celsius * 9) / 5 + 32;
    result = `${celsius}°C is equal to ${fahrenheit.toFixed(2)}°F.`;
  }

  document.getElementById("result").innerText = result;
}
