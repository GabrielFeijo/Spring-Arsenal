
function loginAccount(){
    let info = true;
    for (i=0; i<2;i++){
       if (document.getElementsByClassName('input')[i].value.length == ''){
        info = false;
        showAlert("danger","Erro!","Preencha todas as informações.");
        break;
        }
    }
    if (info == true){
        showAlert("success","Successo!","Você logou na sua conta com sucesso!");
    }
}

document.getElementById('btn-enter').addEventListener("click", loginAccount );