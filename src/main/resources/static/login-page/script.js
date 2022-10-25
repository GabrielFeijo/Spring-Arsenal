
function loginAccount(){
    let info = true;
    for (i=0; i<2;i++){
       if (document.getElementsByClassName('input')[i].value.length == ''){
        info = false;
        showAlert("error");
        break;
        }
    }
    if (info == true){
        showAlert("sucess");
    }
}

//document.getElementById('btn-enter').addEventListener("click", loginAccount );