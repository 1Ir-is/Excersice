function calculateBill() {
  const minutes = parseFloat(document.getElementById("minutes").value);
  let bill;

  if (minutes < 0) {
    bill = "Minutes used cannot be negative.";
  } else if (minutes <= 50) {
    bill = 25; // Flat rate for up to 50 minutes
  } else {
    bill = 25 + (minutes - 50) * 0.15; // $0.15 per minute after the first 50 minutes
  }

  document.getElementById(
    "result"
  ).innerText = `The phone bill is $${bill.toFixed(2)}.`;
}
