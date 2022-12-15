//check name create for typeCar, Brand
function checkNameCreate() {
    const brandName = document.getElementById('nameCreate').value;
    const regex = /^([a-zA-Z ]){2,30}$/
    if (!brandName.match(regex)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("nameCreateCheck").style.display = "block";
        document.getElementById("nameCreate").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("nameCreateCheck").style.display = "none";
        document.getElementById("nameCreate").style.borderColor = "#ced4da";
        return true;
    }
}

//check name has Number not special characters
// modelName
function checkNameCreate2() {
    const modelName = document.getElementById('nameCreate').value;
    const regex = /^[a-zA-Z0-9 \-*_]+$/
    if (!modelName.match(regex)) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("nameCreateCheck").style.display = "block";
        document.getElementById("nameCreate").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("nameCreateCheck").style.display = "none";
        document.getElementById("nameCreate").style.borderColor = "#ced4da";
        return true;
    }
}


function checkSeatNumber() {
    const seatNumber = document.getElementById('seatNumber').value;
    let max = 34, min = 2;
    if (seatNumber < min || seatNumber > max) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("seatNumberCheck").style.display = "block";
        document.getElementById("seatNumber").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("seatNumberCheck").style.display = "none";
        document.getElementById("seatNumber").style.borderColor = "#ced4da";
        return true;
    }
}

function checkNumberPrice() {
    let downPrice = document.getElementById('down').value;
    let up = document.getElementById('up').value;
    downPrice = Number(downPrice);
    up = Number(up);
    if (downPrice < 1 || downPrice > 1000000 || !Number.isInteger(downPrice) || up < downPrice) {
        document.getElementById("create-btn").disabled = true;
        document.getElementById("downCheck").style.display = "block";
        document.getElementById("down").style.borderColor = "red";
        return false;
    } else {
        document.getElementById("create-btn").disabled = false;
        document.getElementById("downCheck").style.display = "none";
        document.getElementById("down").style.borderColor = "#ced4da";
        return true;
    }
}

