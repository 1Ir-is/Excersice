function checkNumber() {
  const number = parseInt(document.getElementById("number").value);
  let result;

  if (number > 0) {
    result = "The number is greater than zero.";
  } else if (number < 0) {
    result = "The number is less than zero.";
  } else {
    result = "The number is equal to zero.";
  }

  document.getElementById("result").innerText = result;
}
