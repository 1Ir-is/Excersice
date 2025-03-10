// let i = 10;
// let f = 20.5;
// let b = true;
// let s = "Hà Nội";


document.write("Bai 3: ");
let a = parseInt(prompt("Enter the first number (a):"));
let b2 = parseInt(prompt("Enter the second number (b):"));

if (a % b2 === 0) {
  alert(a + " is a multiple of " + b2);
} else {
  alert(a + " is not a multiple of " + b2);
}
