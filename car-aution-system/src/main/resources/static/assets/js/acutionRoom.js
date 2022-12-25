

function currentDiv(n) {
    showDivs(slideIndex = n);
}

function allBid(){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/allBid",
        success: function (value) {
            document.getElementById("allBid").innerHTML = value;
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}
setInterval(allBid, 5000);

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



var Gio_hien_tai = document.getElementById("diffHours").value;
var Phut_hien_tai = document.getElementById("diffMinutes").value;
var Giay_hien_tai = document.getElementById("diffSeconds").value;
var timeout = null;

function clock() {
    var gio = document.getElementById("hours");
    var phut = document.getElementById("minutes");
    var giay = document.getElementById("seconds");
    var roomId = $("#roomId").val()
    console.log(roomId)

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
        location.replace("/winPage?roomId="+roomId);
    }

    if (Gio_hien_tai == 0 && Phut_hien_tai == 1 && Giay_hien_tai == 0) {
        Swal.fire('1 minute left')
    }

    if (Gio_hien_tai == 0 && Phut_hien_tai == 0 && Giay_hien_tai == 30) {
        Swal.fire(
            '30 seconds left',
            'Close your final bid',
        )
    }

    if (Gio_hien_tai == 0 && Phut_hien_tai == 0 && Giay_hien_tai == 10) {
        Swal.fire({
            html: 'Bid button locked!',
            timer: 2000,
            didOpen: () => {
                Swal.showLoading()
            },
        })
        document.getElementById('btnBid').setAttribute('onclick', '');
        document.getElementById('btnBid').setAttribute('style', 'background-color: #c0c0c0;');
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

const element3 = document.querySelector("#bided");
let bidPrice = '';
const element = document.querySelector(".yourPrice");
const element2 = document.querySelector(".AVG");
let h5 = '';
let error = '';
let downPrice = 0;
let upPrice = 0;

function isPassAjax1(down , up, roomId) {
    var bid = $("#message2").val();
    downPrice = down;
    upPrice = up;
    const x = Number(bid);
    const y = Number(downPrice);
    const z = Number(upPrice);
    if (document.getElementById("message2").value == "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please do not leave the price blank!',
        });
    } else if(x >= y && x <= z){
        Swal.fire({
            title: 'Are you sure you want to bid?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes'
        }).then((result) => {
            if (result.isConfirmed) {
                h5 = `<h5>${bid} CAB</h5>`;
                element.innerHTML = h5;
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/insertBid?bid=" + bid + "&roomId=" + roomId,
                    data: 'bid:' + bid + 'roomId' + roomId,
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (response) {
                        Swal.fire(
                            'Successfully!',
                            'Your bid has been placed successfully.',
                            'success'
                        )
                        bidPrice += `<p>${bid} CAB</p>`;
                        element3.innerHTML = bidPrice;
                    },
                    error: function (e) {
                        alert('Error: ' + e);
                    }
                });
            }
        })
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please enter a bid within the given bid range!',
        });
    }
}

function closeBanner(){
    document.getElementById("banner-bottom").style.display = 'none';
}