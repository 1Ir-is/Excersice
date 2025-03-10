const exchangeRates = {
  VND: {
    USD: 1 / 23000,
    EUR: 1 / 27000,
    GBP: 1 / 31000,
    JPY: 1 / 200,
    AUD: 1 / 17000,
    CAD: 1 / 18000,
    CHF: 1 / 25000,
    CNY: 1 / 3500,
    KRW: 1 / 20,
    SGD: 1 / 17000,
  },
  USD: {
    VND: 23000,
    EUR: 0.85,
    GBP: 0.75,
    JPY: 110,
    AUD: 1.35,
    CAD: 1.25,
    CHF: 0.92,
    CNY: 6.5,
    KRW: 1150,
    SGD: 1.35,
  },
  EUR: {
    VND: 27000,
    USD: 1.18,
    GBP: 0.88,
    JPY: 129,
    AUD: 1.59,
    CAD: 1.47,
    CHF: 1.08,
    CNY: 7.65,
    KRW: 1350,
    SGD: 1.59,
  },
  GBP: {
    VND: 31000,
    USD: 1.33,
    EUR: 1.14,
    JPY: 146,
    AUD: 1.81,
    CAD: 1.67,
    CHF: 1.23,
    CNY: 8.7,
    KRW: 1530,
    SGD: 1.81,
  },
  JPY: {
    VND: 200,
    USD: 0.0091,
    EUR: 0.0078,
    GBP: 0.0068,
    AUD: 0.012,
    CAD: 0.011,
    CHF: 0.0084,
    CNY: 0.059,
    KRW: 10.5,
    SGD: 0.012,
  },
  AUD: {
    VND: 17000,
    USD: 0.74,
    EUR: 0.63,
    GBP: 0.55,
    JPY: 83,
    CAD: 0.93,
    CHF: 0.68,
    CNY: 4.8,
    KRW: 880,
    SGD: 1,
  },
  CAD: {
    VND: 18000,
    USD: 0.8,
    EUR: 0.68,
    GBP: 0.6,
    JPY: 88,
    AUD: 1.08,
    CHF: 0.73,
    CNY: 5.2,
    KRW: 940,
    SGD: 1.08,
  },
  CHF: {
    VND: 25000,
    USD: 1.09,
    EUR: 0.93,
    GBP: 0.81,
    JPY: 118,
    AUD: 1.47,
    CAD: 1.37,
    CNY: 7.1,
    KRW: 1280,
    SGD: 1.47,
  },
  CNY: {
    VND: 3500,
    USD: 0.15,
    EUR: 0.13,
    GBP: 0.11,
    JPY: 17,
    AUD: 0.21,
    CAD: 0.19,
    CHF: 0.14,
    KRW: 180,
    SGD: 0.21,
  },
  KRW: {
    VND: 20,
    USD: 0.00087,
    EUR: 0.00074,
    GBP: 0.00065,
    JPY: 0.095,
    AUD: 0.0011,
    CAD: 0.001,
    CHF: 0.00078,
    CNY: 0.0056,
    SGD: 0.0011,
  },
  SGD: {
    VND: 17000,
    USD: 0.74,
    EUR: 0.63,
    GBP: 0.55,
    JPY: 83,
    AUD: 1,
    CAD: 0.93,
    CHF: 0.68,
    CNY: 4.8,
    KRW: 880,
  },
};

function exchangeMoney() {
  const amount = parseFloat(document.getElementById("amount").value);
  const fromCurrency = document.getElementById("fromCurrency").value;
  const toCurrency = document.getElementById("toCurrency").value;
  let result;

  if (isNaN(amount) || amount <= 0) {
    document.getElementById("result").innerText = "Please enter a valid amount.";
    return;
  }

  if (fromCurrency === toCurrency) {
    result = amount; // Nếu cùng loại tiền tệ
  } else {
    const rate = exchangeRates[fromCurrency][toCurrency];
    result = amount * rate;
  }

  document.getElementById("result").innerText = `Result: ${result.toFixed(2)} ${toCurrency}`;
}