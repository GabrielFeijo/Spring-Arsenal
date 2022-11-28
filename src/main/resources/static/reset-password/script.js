document.querySelector('.formulario').addEventListener('submit', (event) => {
    event.preventDefault();
   document.querySelector('#btn-enter').disabled = true
    fetch("http://localhost:8080/senha-codigo",
        {
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            method: "POST",
            body: JSON.stringify({
                "email": document.querySelector('#email').value
              
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
                      }, "1500");
                
                }else if(res.status == 400){ 
                    showAlert("info", "Info!", message);                                                         
                }else{
                    showAlert("danger", "Erro!", message);
                }
            });
         
          
    })
    document.querySelector('.formulario').reset();
})

document.querySelector("#return").addEventListener('click', () => {
    window.open("/login", target="_self");
});

