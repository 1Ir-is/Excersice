function checkEligibility() {
  const age = parseInt(document.getElementById("age").value);
  let result;

  if (age >= 15) {
    result = "The student is eligible to enter grade 10.";
  } else {
    result = "The student is not eligible to enter grade 10.";
  }

  document.getElementById("result").innerText = result;
}
