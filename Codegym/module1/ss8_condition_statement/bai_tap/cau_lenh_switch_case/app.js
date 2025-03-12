function countDayInMonth() {
  const month = parseInt(document.getElementById("month").value);
  const year = new Date().getFullYear(); // Get the current year
  let days;

  if (month < 1 || month > 12) {
    document.getElementById("result").innerHTML = "Invalid month";
    return;
  }

  switch (month) {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      days = 31;
      break;

    case 4:
    case 6:
    case 9:
    case 11:
      days = 30;
      break;

    case 2:
      // Check for leap year
      if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
        days = 29;
      } else {
        days = 28;
      }
      break;
  }

  document.getElementById(
    "result"
  ).innerText = `Month ${month} has ${days} days.`;
}
