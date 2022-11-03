const element = document.querySelector(".addModel");

function model(){
    var ele = elements.value;
    if(ele == 'Brand'){
        let option ='';
        option += `<option>Model</option>`
        element.innerHTML = option; 
        return option; 
    }
    if(ele == 1){
        let option ='';
        option += `<option value="BX">BMW X Class</option>`
        option += `<option value="B3">BMW 3 Series</option>`
        option += `<option value="B5">BMW 5 Series</option>`
        option += `<option value="B7">BMW 7 Series</option>`
        element.innerHTML = option; 
        return option; 
    }
    if(ele == 2){
        let option ='';
        option += `<option value="VL">Vinfat Lux</option>`
        option += `<option value="VE">Vinfat Evo</option>`
        option += `<option value="VF">Vinfat Fadil</option>`
        element.innerHTML = option; 
        return option; 
    }
    if(ele == 3){
        let option ='';
        option += `<option value="AA">Audi A Class</option>`
        option += `<option value="AQ">Audi Q Class</option>`
        option += `<option value="AR">Audi RS Class</option>`
        element.innerHTML = option; 
        return option; 
    }
    if(ele == 6){
        let option ='';
        option += `<option value="MC">Mercedes-Benz C Class</option>`
        option += `<option value="ME">Mercedes-Benz E Class</option>`
        option += `<option value="MS">Mercedes-Benz S Class</option>`
        element.innerHTML = option; 
        return option; 
    }
}

var modal = document.getElementById("myModal");
var eye = document.getElementById("eye");
var close = document.getElementsByClassName("close")[0];

eye.onclick = function() {
    modal.style.display = "block";
}

close.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if(event.target == modal) {
        modal.style.display = "none";
    }
}

var modal2 = document.getElementById("myModal2");
var eye2 = document.getElementById("eye2");
var close1 = document.getElementsByClassName("close")[1];

eye2.onclick = function() {
    modal2.style.display = "block";
}

close1.onclick = function() {
    modal2.style.display = "none";
}

window.onclick = function(event) {
    if(event.target == modal2) {
        modal2.style.display = "none";
    }
}
var eye2 = document.getElementById("eye3");

eye3.onclick = function() {
    modal2.style.display = "block";
    modal.style.display = "none";
}


var Gio_hien_tai = 0;
var Phut_hien_tai = 5;
var Giay_hien_tai = 0;
var timeout = null;

function clock() {
    var gio = document.getElementById("hours");
    var phut = document.getElementById("minutes");
    var giay = document.getElementById("seconds");


    if (Giay_hien_tai === -1) {
        Phut_hien_tai -= 1;
        Giay_hien_tai = 59;
    }

    if (Phut_hien_tai === -1) {
        Gio_hien_tai -= 1;
        Phut_hien_tai = 59;
    }

    if (Gio_hien_tai == -1) {
        clearTimeout(timeout);
    }

    timeout = setTimeout(function () {
        Giay_hien_tai--;
        ;
    }, 1000);


    gio.innerHTML = Gio_hien_tai;
    phut.innerHTML = Phut_hien_tai;
    giay.innerHTML = Giay_hien_tai;
}
var Dem_gio = setInterval(clock, 1000);

