function calculateTax() {
  const income = parseFloat(document.getElementById("income").value);
  let result;
  let tax = 0;

  /* Tham khảo ở https://viblo.asia/p/tu-tao-cong-cu-tinh-thue-thu-nhap-ca-nhan-2020-Qbq5Q0EmlD8
        Bậc	    Thu nhập tính thuế/tháng	Thuế suất	Tính số thuế phải nộp
        1	    Đến 5 triệu đồng (trđ)	    5%	        5% x TNTT
        2	    Trên 5 trđ đến 10 trđ	    10%	        10% TNTT - 0,25 trđ
        3	    Trên 10 trđ đến 18 trđ	    15%	        15% TNTT - 0,75 trđ
        4	    Trên 18 trđ đến 32 trđ	    20%	        20% TNTT - 1,65 trđ
        5	    Trên 32 trđ đến 52 trđ	    25%	        25% TNTT - 3,25 trđ
        6	    Trên 52 trđ đến 80 trđ	    30%	        30 % TNTT - 5,85 trđ
        7	    Trên 80 trđ	                35%	        35% TNTT - 9,85 trđ
    */

  // Check if the input is a valid number
  if (isNaN(income) || income < 0) {
    result = "Please enter a valid income.";
  } else {
    // Calculate the tax based on the income using the provided tax brackets and rates
    if (income <= 5000000) {
      // Bậc 1: Đến 5 triệu đồng (5% x TNTT)
      tax = income * 0.05;
    } else if (income <= 10000000) {
      // Bậc 2: Trên 5 triệu đến 10 triệu (10% TNTT - 0,25 triệu)
      tax = income * 0.1 - 250000;
    } else if (income <= 18000000) {
      // Bậc 3: Trên 10 triệu đến 18 triệu (15% TNTT - 0,75 triệu)
      tax = income * 0.15 - 750000;
    } else if (income <= 32000000) {
      // Bậc 4: Trên 18 triệu đến 32 triệu (20% TNTT - 1,65 triệu)
      tax = income * 0.2 - 1650000;
    } else if (income <= 52000000) {
      // Bậc 5: Trên 32 triệu đến 52 triệu (25% TNTT - 3,25 triệu)
      tax = income * 0.25 - 3250000;
    } else if (income <= 80000000) {
      // Bậc 6: Trên 52 triệu đến 80 triệu (30% TNTT - 5,85 triệu)
      tax = income * 0.3 - 5850000;
    } else {
      // Bậc 7: Trên 80 triệu (35% TNTT - 9,85 triệu)
      tax = income * 0.35 - 9850000;
    }
    result = `The personal income tax for an income of ${income.toLocaleString()} VND is ${tax.toLocaleString()} VND.`;
  }

  // Display the result
  document.getElementById("result").innerText = result;
}
