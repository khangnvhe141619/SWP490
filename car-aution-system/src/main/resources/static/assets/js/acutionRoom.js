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
let i = 1;
const element = document.querySelector(".yourPrice");
const element2 = document.querySelector(".AVG");
let h5 = '';
let avgText = '';
let error = '';
let downPrice = 0;
let upPrice = 0;
let a = 0;
let b = 0;

function isPassAjax1(down , up, roomId) {
    var bid = $("#message2").val();
    var avg = $("#AVG").val();
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
                console.log(bid);
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

                        bidPrice += `<span>${i}. ${bid} CAB</span></br>`;
                        element3.innerHTML = bidPrice;
                        i++;
                        if(a==0){
                            b = bid;
                        } else {
                            b = (x + Number(a))/2;
                        }
                        a = bid;
                        avgText = `<span>Average: ${b} CAB</span>`;
                        element2.innerHTML = avgText;

                        document.getElementById("btnBid").click();
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
var usernamePage2 = document.querySelector('#username-page2');
var chatPage2 = document.querySelector('#chat-page2');
var usernameForm2 = document.querySelector('#usernameForm2');
var messageForm2 = document.querySelector('#messageForm2');
var messageInput2 = document.querySelector('#message2');
var messageArea2 = document.querySelector('#messageArea2');
var connectingElement2 = document.querySelector('.connecting');

var stompClient2 = null;
var username2 = null;

function connect2(event) {
    username2 = document.querySelector('#name2').value.trim();

    if (username2) {
        usernamePage2.classList.add('hidden');
        chatPage2.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient2 = Stomp.over(socket);

        stompClient2.connect({}, onConnected2, onError2);
    }
    event.preventDefault();
}


function onConnected2() {
    // Subscribe to the Public Topic
    stompClient2.subscribe('/topic/public', onMessageReceived2);

    // Tell your username to the server
    stompClient2.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username2, type: 'JOIN'})
    )

    connectingElement2.classList.add('hidden');
}


function onError2(error) {
    connectingElement2.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement2.style.color = 'red';
}


function sendMessage2(event) {
    var messageContent2 = messageInput2.value.trim();
    if (messageContent2 && stompClient2) {
        var chatMessage2 = {
            sender: username2,
            content: messageInput2.value,
            type: 'CHAT'
        };
        stompClient2.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage2));
        messageInput2.value = '';
    }
    event.preventDefault();
}


function onMessageReceived2(payload) {
    var message = JSON.parse(payload.body);
    if ((!isNaN(message.content) && message.content) && (message.content >= downPrice && message.content <= upPrice)) {
        var messageElement = document.createElement('li');

        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('img');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.src = '/assets/img/avatar/andanh.jpg';

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode("***");
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content + ' CAB');
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea2.appendChild(messageElement);
        messageArea2.scrollTop = messageArea2.scrollHeight;
    }
}
usernameForm2.addEventListener('submit', connect2, true)
messageForm2.addEventListener('submit', sendMessage2, true)

function closeBanner(){
    document.getElementById("banner-bottom").style.display = 'none';
}