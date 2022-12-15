
function checkNumberPriceUp() {
    var down = document.getElementById("down").value;
    var up = document.getElementById('up').value;
    down = Number(down);
    up = Number(up);
    if (up < down || !Number.isInteger(up)){
        document.getElementById("create-btn").disabled = true;
        document.getElementById("upCheck").style.display = "block";
        document.getElementById("up").style.borderColor = "red";
        return false;
    }else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("upCheck").style.display = "none";
        document.getElementById("up").style.borderColor = "#ced4da";
        document.getElementById("downCheck").style.display = "none";
        document.getElementById("down").style.borderColor = "#ced4da";
        return true;
    }
}

function checkNameCreateManu() {
    const brandName = document.getElementById('manufacturer').value;
    const regex = /^[a-zA-Z0-9 \-*_]+$/
    if (!brandName.match(regex)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("manufacturerCheck").style.display = "block";
        document.getElementById("manufacturer").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("manufacturerCheck").style.display = "none";
        document.getElementById("manufacturer").style.borderColor = "#ced4da";
        return true;
    }
}

function checkOuterColor() {
    const brandName = document.getElementById('outerColor').value;
    const regex = /^([a-zA-Z ]){2,30}$/
    if (!brandName.match(regex)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("outerColorCheck").style.display = "block";
        document.getElementById("outerColor").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("outerColorCheck").style.display = "none";
        document.getElementById("outerColor").style.borderColor = "#ced4da";
        return true;
    }
}

function innerColorColor() {
    const brandName = document.getElementById('innerColor').value;
    const regex = /^([a-zA-Z ]){2,30}$/
    if (!brandName.match(regex)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("innerColorCheck").style.display = "block";
        document.getElementById("innerColor").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("innerColorCheck").style.display = "none";
        document.getElementById("innerColor").style.borderColor = "#ced4da";
        return true;
    }
}

function checkNumberKm() {
    let km = document.getElementById('km_driven').value;
    km = Number(km);
    if (km < 0 || !Number.isInteger(km)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("km_drivenCheck").style.display = "block";
        document.getElementById("km_driven").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("km_drivenCheck").style.display = "none";
        document.getElementById("km_driven").style.borderColor = "#ced4da";
        return true;
    }
}

function checkFuelConsumption() {
    let km = document.getElementById('fuelConsume').value;
    km = Number(km);
    if (km < 0) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("fuelConsumeCheck").style.display = "block";
        document.getElementById("fuelConsume").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("fuelConsumeCheck").style.display = "none";
        document.getElementById("fuelConsume").style.borderColor = "#ced4da";
        return true;
    }
}

function checkYear() {
    let km = document.getElementById('yearOfMake').value;
    var d = new Date();
    var year = d.getFullYear()
    km = Number(km);
    year = Number(year);
    if (km < 2000 || km > year+1 || !Number.isInteger(km)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("yearOfMakeCheck").style.display = "block";
        document.getElementById("yearOfMake").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("yearOfMakeCheck").style.display = "none";
        document.getElementById("yearOfMake").style.borderColor = "#ced4da";
        return true;
    }
}

function checkBag() {
    let km = document.getElementById('bag').value;
    km = Number(km);
    if (km < 0 || !Number.isInteger(km)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("bagCheck").style.display = "block";
        document.getElementById("bag").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("bagCheck").style.display = "none";
        document.getElementById("bag").style.borderColor = "#ced4da";
        return true;
    }
}