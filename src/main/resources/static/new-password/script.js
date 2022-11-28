document.querySelector('.formulario').addEventListener('submit', (event) => {
    event.preventDefault();
    const campos =  document.getElementsByClassName('input')

        if (campos[0].value === campos[1].value){
           let code =  getParameter('code');

            fetch("http://localhost:8080/senha-alterar",
            {
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                method: "POST",
                body: JSON.stringify({
                    "codigoRecuperacao": code,
                    "senha": campos[0].value
                  
                })
            })
            .then(res => {   
                let myPromise = res.text()
                
                myPromise.then((message) => {  
                    document.querySelector('#btn-enter').disabled = false
                     if (res.status == 200){
                        showAlert("success", "Sucesso!", message);
                        setTimeout(() => {
                            window.open("/login", target="_self");
                          }, "1700");                                          
                    }else if(res.status == 400){ 
                        showAlert("info", "Info!", message);  
                        setTimeout(() => {
                            window.open("/esqueceu-a-senha", target="_self");
                          }, "2500");                                       
                    }else{
                        showAlert("danger", "Erro!", message);
                    }
                });
             
              
        })

            document.querySelector('.formulario').reset();
        }else{
            showAlert("danger", "Erro!", "As senhas digitadas não coincidem!");
        }
   
  
})

function getParameter(theParameter) {
    var params = window.location.search.substr(1).split('&');

    for (var i = 0; i < params.length; i++) {
        var p = params[i].split('=');
        if (p[0] == theParameter) {
            return decodeURIComponent(p[1]);
        }
    }
    return false;
}


document.querySelector("#return").addEventListener('click', () => {
    window.open("/login", target="_self");
});

