function calculateBMI() {
  let weight = document.getElementById("weight").value;
  let height = document.getElementById("height").value;

  if (weight > 0 && height > 0) {
    let heightInMeters = height / 100;
    let bmi = weight / (heightInMeters * heightInMeters);

    let category = "";
    if (bmi < 18.5) {
      category = "Underweight";
    } else if (bmi >= 18.5 && bmi < 25.0) {
      category = "Normal";
    } else if (bmi >= 25.0 && bmi < 30.0) {
      category = "Overweight";
    } else {
      category = "Obese";
    }

    document.getElementById("result").innerHTML = `Your BMI is ${bmi.toFixed(
      2
    )} and you are ${category}`;
  } else {
    alert("Please enter valid values");
  }
}
