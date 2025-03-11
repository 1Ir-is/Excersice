function calculateBill() {
  const units = parseFloat(document.getElementById("units").value);
  let result;
  let bill = 0;

  /*  Tham khảo công thức tính ở https://www.quangninhbay.com/2020/06/giai-bai-toan-viet-chuong-trinh-tinh.html
    TT     Số kWh sử dụng          Giá bán điện (đồng/kWh)
    Bậc 1  Cho kWh từ 0 - 50       1.678
    Bậc 2  Cho kWh từ 51 - 100     1.734
    Bậc 3  Cho kWh từ 101 - 200    2.014
    Bậc 4  Cho kWh từ 201 - 300    2.536
    Bậc 5  Cho kWh từ 301 - 400    2.834
    Bậc 6  Cho kWh từ 401 trở lên  2.927
*/
  if (isNaN(units) || units < 0) {
    result = "Please enter a valid number of units.";
  } else {
    // TT  Số kWh sử dụng  Giá bán điện (đồng/kWh)
    // Bậc 1  Cho kWh từ 0 - 50  1.678
    if (units <= 50) {
      bill = units * 1.678;
    }
    // Bậc 2  Cho kWh từ 51 - 100  1.734
    else if (units <= 100) {
      bill = 50 * 1.678 + (units - 50) * 1.734;
    }
    // Bậc 3  Cho kWh từ 101 - 200  2.014
    else if (units <= 200) {
      bill = 50 * 1.678 + 50 * 1.734 + (units - 100) * 2.014;
    }
    // Bậc 4  Cho kWh từ 201 - 300  2.536
    else if (units <= 300) {
      bill = 50 * 1.678 + 50 * 1.734 + 100 * 2.014 + (units - 200) * 2.536;
    }
    // Bậc 5  Cho kWh từ 301 - 400  2.834
    else if (units <= 400) {
      bill =
        50 * 1.678 +
        50 * 1.734 +
        100 * 2.014 +
        100 * 2.536 +
        (units - 300) * 2.834;
    }
    // Bậc 6  Cho kWh từ 401 trở lên  2.927
    else {
      bill =
        50 * 1.678 +
        50 * 1.734 +
        100 * 2.014 +
        100 * 2.536 +
        100 * 2.834 +
        (units - 400) * 2.927;
    }
    result = `The electricity bill for ${units} units is ${bill.toFixed(
      2
    )} VND.`;
  }

  document.getElementById("result").innerText = result;
}
