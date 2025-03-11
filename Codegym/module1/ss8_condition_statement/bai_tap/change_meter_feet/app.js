function convertToFeet() {
    const meters = parseFloat(document.getElementById('meters').value);
    let result;

    if (isNaN(meters)) {
        result = 'Please enter a valid number.';
    } else {
        const feet = meters * 3.28084;
        result = `${meters} meters is equal to ${feet.toFixed(2)} feet.`;
    }

    document.getElementById('result').innerText = result;
}