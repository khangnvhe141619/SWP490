const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");
const forgot_btn = document.querySelector("#forgot-btn");
var password = document.getElementsByClassName("password").value;
var repassword = document.getElementsByClassName("repassword").value;
var iconLoading = document.getElementById("loading");


function showLoading () {
    iconLoading.style.display = "block";
}
function hideLoading (){
    iconLoading.style.display = "none";
}
function getHome (){
    window.location.assign("./home")
}


function onSubmitForm () {
    showLoading();
    setTimeout(hideLoading, 1000);

    var username = $("#username").val();
    var password2 = $("#password").val();
    $.ajax({
        type: "POST",
        url: serverContext + "login?username=" + username +"&password=" + password2,
        data: 'username:' + username + 'password:' + password2,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            if (response.status == "yes") {
                setTimeout(getHome, 1000);
            } else if (response.status == "no") {
                function message (){
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: response.message,
                    });
                }
                setTimeout(message, 1000);

            }else if (response.status == "ban") {
                function message (){
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: response.message,
                    });
                }
                setTimeout(message, 1000);
            }
        },
        error: function(e){
            alert('Error: ' + e);
        }
    });
}


sign_up_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
    document.getElementById("forgot").style.display = "none";
});

forgot_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
    document.getElementById("signup").style.display = "none";
});

sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
    document.getElementById("signup").style.display = "flex";
    document.getElementById("forgot").style.display = "flex";
});

isBool = true;

function showHidden() {
    if (isBool) {
        document.getElementById("password").setAttribute("type", "text");
        document.getElementById("eye").setAttribute("class", "fas fa-eye-slash");
        isBool = false;
    } else {
        document.getElementById("password").setAttribute("type", "password");
        document.getElementById("eye").setAttribute("class", "fas fa-eye");
        isBool = true;
    }
}