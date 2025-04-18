function clearDisplay() {
  document.getElementById("display").innerText = "0";
}

function deleteLastChar() {
  const display = document.getElementById("display");
  display.innerText = display.innerText.slice(0, -1);
  if (display.innerText === "") {
    display.innerText = "0";
  }
}

function appendToDisplay(value) {
  const display = document.getElementById("display");
  const lastChar = display.innerText.slice(-1);

  if (display.innerText === "0" && !["+", "-", "*", "/"].includes(value)) {
    display.innerText = value;
  } else if (
    ["+", "-", "*", "/"].includes(lastChar) &&
    ["+", "-", "*", "/"].includes(value)
  ) {
    display.innerText = display.innerText.slice(0, -1) + value;
  } else {
    display.innerText += value;
  }
}

function add(a, b) {
  return a + b;
}

function subtract(a, b) {
  return a - b;
}

function multiply(a, b) {
  return a * b;
}

function divide(a, b) {
  if (b === 0) {
    return a > 0 ? "Infinity" : "-Infinity"; // Allow division by zero
  }
  return a / b;
}

function calculateResult() {
  const display = document.getElementById("display");
  try {
    const expression = display.innerText;
    const operator = expression.match(/[+\-*/]/)[0];
    const [operand1, operand2] = expression.split(operator);
    const a = parseFloat(operand1);
    const b = parseFloat(operand2);

    if (["+", "-", "*", "/"].includes(expression.slice(-1))) {
      alert("Invalid expression");
    }

    let result;
    switch (operator) {
      case "+":
        result = add(a, b);
        break;
      case "-":
        result = subtract(a, b);
        break;
      case "*":
        result = multiply(a, b);
        break;
      case "/":
        result = divide(a, b);
        break;
      default:
        alert("Invalid operator");
    }

    display.innerText = parseFloat(result.toFixed(2));
  } catch {
    display.innerText = "Error";
  }
}
