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

function changePass(){
    Swal.fire({
        title: 'Do you want to save the changes?',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Save',
        denyButtonText: `Don't save`,
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire('Saved!', '', 'success')
            document.getElementById("btnSubmit").click();
        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info')
        }
    })
}

function changeProfile(){
    Swal.fire({
        title: 'Do you want to save the changes?',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Save',
        denyButtonText: `Don't save`,
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire('Saved!', '', 'success')
            document.getElementById("btnSubmitProfile").click();
        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info')
        }
    })
}