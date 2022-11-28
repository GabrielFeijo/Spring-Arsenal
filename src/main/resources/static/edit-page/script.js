
 
let dados = {}
var num = 3
let trilhas = ["Persona","Proposta de valor","Prototipação","MVP","Validação","Descobrimento do problema","Ideação"];

async function fetchQuestionsJSON() {
    const response = await fetch('http://localhost:8080/perguntas');
    const questions = await response.json();
    return questions;
  
  }
  fetchQuestionsJSON().then(questions => {
    questions; // fetched questions
    dados = questions
 
    addOptions(0);

    
});


function addOptions(num){

    let select = document.getElementsByClassName('select')

    for (s=num;s<select.length;s++){
       select[s].innerHTML = ''
       var disabled = document.createElement('option');
       disabled.value ='none';
       disabled.innerHTML ='Alternativa '+(s+1);
       disabled.disabled = true;
       disabled.selected = true;
       select[s].appendChild(disabled);

       for(i=0;i<trilhas.length;i++){
            let trilha = document.createElement('option');
            trilha.value = trilhas[i];
            trilha.innerHTML = trilhas[i];
        
            select[s].appendChild(trilha);
       }

        for (i=0;i<dados.length;i++){
            var opt = document.createElement('option');
            opt.value = dados[i]['idpergunta'];
            opt.innerHTML = dados[i]['pergunta'];
        
            select[s].appendChild(opt);
        }

    }
    
    
}





document.querySelector('.add').addEventListener('click',() => {
    let local = document.querySelector('.response');
    let input = document.createElement('INPUT');
    input.setAttribute("type","text");
    input.setAttribute("id","answer"+num);
    input.classList.add('input', 'mt-4','resp')
    input.placeholder = "Resposta "+num;

    local.appendChild(input);
    let ldiv = document.querySelector('.next')
    let div = document.createElement('div')
    div.classList.add('mb-4');
    let label = document.createElement('label')
    label.setAttribute("for","select"+num);
    label.innerText = "Próxima pergunta | Trilha:";

    const select = document.createElement('select')
    select.classList.add('input', 'select','ms-1');
    
    div.appendChild(label);
    div.appendChild(select);

     ldiv.appendChild(div);
    addOptions(num-1);
    num++
    
});

document.querySelector('#continue').addEventListener('click', (event) => {
    event.preventDefault();
    const pergunta = document.querySelector('#query').value;

    const respostas = document.getElementsByClassName('resp')
    let respJ = []
    for (r=0;r<respostas.length;r++){
        respJ[r] = respostas[r].value;
     
    }

    let next = document.getElementsByClassName('select')
    
    let nextID = []
    for (s=0;s<next.length;s++){
  
       nextID[s] =  next[s].value;
    }

    if (pergunta.length != 0){
        fetch("http://localhost:8080/perguntas",
        {
            headers:{
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            method: "POST",
            body: JSON.stringify({
                "pergunta": pergunta,
                "alternativas": JSON.stringify(respJ),
                "proximapergunta":  JSON.stringify(nextID)
        
               
            })
        })
        .then(function(res) {console.log(res)})
        .catch(function(res) {console.log(res)})
        document.querySelector('.newQuestion').reset();

    }

});