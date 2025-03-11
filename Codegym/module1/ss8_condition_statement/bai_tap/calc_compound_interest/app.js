function calculateCompoundInterest() {
  const principal = parseFloat(document.getElementById("principal").value);
  const months = parseInt(document.getElementById("months").value);
  const rate = parseFloat(document.getElementById("rate").value) / 100;
  let result;

  /* Lãi mẹ đẻ lãi con có nghĩa là lãi suất kép
    Đã tham khảo công thức tính lãi suất kép ở https://hdbank.com.vn/vi/news/detail/tin-tuc-khac/cong-thuc-tinh-lai-kep
*/
  // Kiểm tra xem các giá trị đầu vào có hợp lệ không
  if (
    isNaN(principal) ||
    principal <= 0 ||
    isNaN(months) ||
    months <= 0 ||
    isNaN(rate) ||
    rate <= 0
  ) {
    result = "Please enter valid positive numbers for all fields.";
  } else {
    /* Tính lãi suất kép
     Công thức tính lãi suất kép: A = P(1 + r)^n
     Trong đó:
     A là số tiền cuối cùng, => amount
     P là số tiền gốc, => principal
     r là lãi suất, => rate
     n là số tháng gửi tiền, => months
    */
    const amount = principal * Math.pow(1 + rate, months);
    result = `The total amount after ${months} months is ${amount.toFixed(
      2
    )} VND.`;
  }

  // Hiển thị kết quả
  document.getElementById("result").innerText = result;
}
