function editProfile(){
    document.getElementById("person-profile").style.display = "none";
    document.getElementById("acc-setting").style.display = "none";
    document.getElementById("acc-update").style.display = "none";
    document.getElementById("update-profile").style.display = "block";
}
function accSetting(){
    document.getElementById("person-profile").style.display = "none";
    document.getElementById("acc-setting").style.display = "none";
    document.getElementById("update-profile").style.display = "none";
    document.getElementById("acc-update").style.display = "block";
}

function verifyPassword() {
    var np = document.getElementById("newPass").value;
    var rp = document.getElementById("rePass").value;

    if(np != rp){
        document.getElementById("message2").innerHTML = "New Password and Re Password have to be the same!";
        return false;
    }
}