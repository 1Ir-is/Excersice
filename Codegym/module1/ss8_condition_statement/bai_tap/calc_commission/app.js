function calculateCommission() {
  const sales = parseFloat(document.getElementById("sales").value);
  let commission;

  if (sales < 0) {
    commission = "Sales amount cannot be negative.";
  } else if (sales <= 5000) {
    commission = sales * 0.02;
  } else if (sales <= 10000) {
    commission = sales * 0.05;
  } else {
    commission = sales * 0.1;
  }

  document.getElementById(
    "result"
  ).innerText = `The commission is $${commission.toFixed(2)}.`;
}
