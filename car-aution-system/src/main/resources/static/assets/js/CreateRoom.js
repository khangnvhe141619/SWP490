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

function checkDate(openDate, startTime, roomId) {
    var x = document.cookie;
    if (x == null || x == "") {
        location.href = "/login";
    } else {
        let currentDate = new Date();
        var date = openDate.toString() + ' ' + startTime.toString();
        let openDay = new Date(date);

        if (currentDate.getDate() - openDay.getDate() + currentDate.getMonth() - openDay.getMonth() == 0) {
            if (currentDate.getHours() - openDay.getHours() + currentDate.getMinutes() - openDay.getMinutes()
                + currentDate.getSeconds() - openDay.getSeconds() >= 0) {
                location.href = "/auctionRoom/" + roomId;
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'The auction room hasn\'t started yet\n' +
                        ', Please wait until now!',
                });
            }
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'The auction room hasn\'t started yet\n' +
                    ', Please wait until now!',
            });
        }
    }
}

const element = document.querySelector(".addModel");

function model(){
    var ele = elements.value;
    if(ele == 'Brand'){
        let option ='';
        option += `<option>Model</option>`
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

function closeBanner(){
    document.getElementById("banner-bottom").style.display = 'none';
}
