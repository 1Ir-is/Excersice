function convertTemperature() {
  let celsius = document.getElementById("celsius").value;
  let fahrenheit = (celsius * 9) / 5 + 32;
  document.getElementById(
    "result"
  ).innerText = `${celsius}°C = ${fahrenheit.toFixed(2)}°F`;
}
