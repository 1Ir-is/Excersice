function exchangeMoney() {
  const amount = document.getElementById("amount").value;
  const currency = document.getElementById("currency").value;
  const result = document.getElementById("result");
  const exchangeRate = {
    vnd_to_usd: 0.000043,
    usd_to_vnd: 26000,
  };

  if (amount === "" || isNaN(amount)) {
    result.textContent = "Please enter a valid amount";
    return;
  }

  let exchangedAmount;
  if (currency === "vnd_to_usd") {
    exchangedAmount = amount * exchangeRate.vnd_to_usd;
    result.textContent = `${amount} VND = ${exchangedAmount.toFixed(2)} USD`;
  } else {
    exchangedAmount = amount * exchangeRate.usd_to_vnd;
    result.textContent = `${amount} USD = ${exchangedAmount.toFixed(0)} VND`;
  }
}
