function calculateBill() {
  const units = parseFloat(document.getElementById("units").value);
  let result;
  let bill = 0;

  /*  Tham khảo công thức tính ở https://zalopay.vn/cach-tinh-tien-dien-2655
    TT     Số kWh sử dụng          Giá bán điện (đồng/kWh)
    Bậc 1  Cho kWh từ 0 - 50       1.806
    Bậc 2  Cho kWh từ 51 - 100     1.866
    Bậc 3  Cho kWh từ 101 - 200    2.167
    Bậc 4  Cho kWh từ 201 - 300    2.729
    Bậc 5  Cho kWh từ 301 - 400    3.050
    Bậc 6  Cho kWh từ 401 trở lên  3.151
  */

  let bac1 = 1.806;
  let bac2 = 1.866;
  let bac3 = 2.167;
  let bac4 = 2.729;
  let bac5 = 3.05;
  let bac6 = 3.151;

  if (isNaN(units) || units < 0) {
    result = "Please enter a valid number of units.";
  } else {
    // TT  Số kWh sử dụng  Giá bán điện (đồng/kWh)
    // Bậc 1  Cho kWh từ 0 - 50  1.806
    if (units <= 50) {
      bill = units * bac1;
    }
    // Bậc 2  Cho kWh từ 51 - 100  1.866
    else if (units <= 100) {
      bill = 50 * bac1 + (units - 50) * bac2;
    }
    // Bậc 3  Cho kWh từ 101 - 200  2.167
    else if (units <= 200) {
      bill = 50 * bac1 + 50 * bac2 + (units - 100) * bac3;
    }
    // Bậc 4  Cho kWh từ 201 - 300  2.729
    else if (units <= 300) {
      bill = 50 * bac1 + 50 * bac2 + 100 * bac3 + (units - 200) * bac4;
    }
    // Bậc 5  Cho kWh từ 301 - 400  3.050
    else if (units <= 400) {
      bill =
        50 * bac1 + 50 * bac2 + 100 * bac3 + 100 * bac4 + (units - 300) * bac5;
    }
    // Bậc 6  Cho kWh từ 401 trở lên  3.151
    else {
      bill =
        50 * bac1 +
        50 * bac2 +
        100 * bac3 +
        100 * bac4 +
        100 * bac5 +
        (units - 400) * bac6;
    }
    result = `The electricity bill for ${units} units is ${bill.toFixed(
      3
    )} VND.`;
  }

  document.getElementById("result").innerText = result;
}
