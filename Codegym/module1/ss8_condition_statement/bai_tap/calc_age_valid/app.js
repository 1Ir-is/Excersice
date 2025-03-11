function checkAge() {
  const age = parseInt(document.getElementById("age").value);
  let result;

  if (isNaN(age) || age <= 0 || age >= 120) {
    result = "Please enter a valid age between 1 and 119.";
  } else {
    result = `The age ${age} is valid.`;
  }

  document.getElementById("result").innerText = result;
}
