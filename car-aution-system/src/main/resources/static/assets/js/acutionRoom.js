function currentDiv(n) {
    showDivs(slideIndex = n);
}

function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("demo");
    if (n > x.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = x.length }
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
    }
    x[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " w3-opacity-off";
}

var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    myIndex++;
    if (myIndex > x.length) { myIndex = 1 }
    x[myIndex - 1].style.display = "block";
    setTimeout(carousel, 9000);
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
        location.replace("http://127.0.0.1:5500/winner.html");
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

function openPage(pageName, elmnt, color) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].style.backgroundColor = "";
        tablinks[i].style.color = "";
    }
    document.getElementById(pageName).style.display = "block";
    elmnt.style.backgroundColor = color;
    elmnt.style.color = "#fff";
}

document.getElementById("defaultOpen").click();

src = "https://kit.fontawesome.com/64d58efce2.js"
crossorigin = "anonymous"

const element = document.querySelector(".yourPrice");
const element2 = document.querySelector(".error");
let h5 = '';
let error = '';
function yourPrice() {
    if (document.getElementById("inputPrie").value == 0 || document.getElementById("inputPrie").value == null) {
        error += `Please do not leave the price blank or equal to 0`;
        element2.innerHTML = error;
    } else {
        if(confirm("Are you sure you want to bid?")==true){
            document.getElementById('error').remove();
            h5 += `<h5>${document.getElementById("inputPrie").value} CAB</h5>`;
            document.getElementById('btnBid').setAttribute('onclick', '');
            document.getElementById('btnBid').setAttribute('style', 'background-color: #c0c0c0;');
            element.innerHTML = h5;
            return h5;
        }else{
            return null;
        }
    }
}