const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");
const forgot_btn = document.querySelector("#forgot-btn");
var password = document.getElementsByClassName("password").value;
var repassword = document.getElementsByClassName("repassword").value;

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
    function showHidden(){
        if(isBool){
            document.getElementById("password").setAttribute("type","text");
            document.getElementById("eye").setAttribute("class","fas fa-eye-slash");
            isBool = false;
        } else {
            document.getElementById("password").setAttribute("type","password");
            document.getElementById("eye").setAttribute("class","fas fa-eye");
            isBool = true;
        }
    }

    function check(){
        alert(repassword + " " + password);
      }