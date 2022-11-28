

let dados = {}
var num = 0
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
    console.log(num)
    for (s=num;s<select.length;s++){
       select[s].innerHTML = ''
       
        if (s > 0) {
            var disabled = document.createElement('option');
            disabled.value ='none';
            disabled.innerHTML ='Alternativa '+(s);
            disabled.disabled = true;
            disabled.selected = true;
            select[s].appendChild(disabled);
     
            for(i=0;i<trilhas.length;i++){
                let trilha = document.createElement('option');
                trilha.value = trilhas[i];
                trilha.innerHTML = trilhas[i];
            
                select[s].appendChild(trilha);
           }
        }

        for (i=0;i<dados.length;i++){
            var opt = document.createElement('option');
            opt.value = dados[i]['idpergunta'];
            opt.innerHTML = dados[i]['pergunta'];
        
            select[s].appendChild(opt);
        }
    } 
    
}
let id;
document.querySelector('#select').addEventListener('change', () => {
    let question = document.querySelector('#select').value
    num = 0
    for (i=0;i<dados.length;i++){
        if (question == dados[i]['idpergunta'] ){
            question = i;
            break;
        }
    }

    let edit = document.querySelector("#query");
    edit.innerHTML =""
    let innputEdit = document.createElement('INPUT');
    innputEdit.setAttribute("type","text");
    innputEdit.setAttribute("id","editQuestion");
    innputEdit.classList.add('input', 'mt-4');
    innputEdit.value = dados[question]['pergunta'];
    edit.appendChild(innputEdit);

    let local = document.querySelector('.response');
    local.innerHTML = ""
    let ldiv = document.querySelector('.next')
    ldiv.innerHTML = ""
    let label = document.createElement('label')
    label.innerText = "Possíveis Respostas: ";
    local.appendChild(label);   
    let respostas = JSON.parse(dados[question]['alternativas']);
    let prox = JSON.parse(dados[question]['proximapergunta']);
    id = dados[question]['idpergunta'];
    
    local.classList.remove('d-none')
    document.querySelector('.add').classList.remove('d-none')
    document.querySelector('.buttons').classList.remove('d-none')
    ldiv.classList.remove('d-none')
   
    for (i=0;i<respostas.length;i++){
      
        let input = document.createElement('INPUT');
        input.setAttribute("type","text");
        input.setAttribute("id","answer"+i);
        input.classList.add('input', 'mt-4','resp')
        input.value = respostas[i];
        console.log(respostas[i])
        local.appendChild(input);
     
        let div = document.createElement('div')
        div.classList.add('mb-4');
        let label = document.createElement('label')
        label.setAttribute("for","select"+num);
        label.innerText = "Próxima pergunta:";       
    
        const select = document.createElement('select')
        select.classList.add('input', 'select','ms-1','nextID');
        let opt = document.createElement('option');
        opt.value = "none";
        opt.innerHTML = "Selecione!";
        select.appendChild(opt);

        for (d=0;d<dados.length;d++){
            opt = document.createElement('option');
            opt.value = dados[d]['idpergunta'];
            opt.innerHTML = dados[d]['pergunta'];
        
            select.appendChild(opt);
        }
               

        for(t=0;t<trilhas.length;t++){
            let trilha = document.createElement('option');
            trilha.value = trilhas[t];
            trilha.innerHTML = trilhas[t];
        
            select.appendChild(trilha);
       }
        
        if (Number(prox[i])){
            select[prox[i]].selected = true;
        }else{
            
            for (var p = 0; p < select.options.length; p++) {
              
                if ((select.options[p].text).toLowerCase() === prox[i].toLowerCase()) {
                    
                    select.selectedIndex = p;
                    select[p].selected = true
                   
                    break;
                }
            }
        }

        div.appendChild(label);
        div.appendChild(select);
        num++
     
        ldiv.appendChild(div);
        
    }   
    
});


document.querySelector('.add').addEventListener('click',() => {
    let local = document.querySelector('.response');
    let input = document.createElement('INPUT');
    input.setAttribute("type","text");
    input.setAttribute("id","answer"+(num + 1));
    input.classList.add('input', 'mt-4','resp')
    input.placeholder = "Resposta "+(num + 1);
    local.appendChild(input);

    let ldiv = document.querySelector('.next')
    let div = document.createElement('div')
    div.classList.add('mb-4');
    let label = document.createElement('label')
    label.setAttribute("for","select"+num);
    label.innerText = "Próxima pergunta:";
   
    const select = document.createElement('select')
    select.classList.add('input', 'select','ms-1','nextID');
    
    div.appendChild(label);
    div.appendChild(select);

    ldiv.appendChild(div);
    num++
    addOptions(num);
    
});

document.querySelector('#send').addEventListener('click', () => {
    
    let pergunta = document.querySelector('#editQuestion').value;
  
    let respostas = document.getElementsByClassName('resp')
    let respJ = []
    for (r=0;r<respostas.length;r++){
        if (respostas[r].value != ""){
            respJ[r] = respostas[r].value;
        }      
    }

    let next = document.getElementsByClassName('nextID')
    
    let nextID = []
    for (s=0;s<next.length;s++){
        if (next[s].value != 'none'){
            nextID[s] =  next[s].value;
            console.log( next[s].value)
        }
    
    }

    if (pergunta.length != 0){
        fetch("http://localhost:8080/perguntas",
        {
            headers:{
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify({
                "idpergunta":id,
                "pergunta": pergunta,
                "alternativas": JSON.stringify(respJ),
                "proximapergunta":  JSON.stringify(nextID)                     
            })
        })
        .then(function(res) {console.log(res)})
        .catch(function(res) {console.log(res)})
        document.querySelector('.editQuestion').reset();
    }
});

document.querySelector('#confirm').addEventListener('click', showBox);
document.querySelector('#cancel').addEventListener('click', showBox);


function showBox(event){
    if (event){
        event.preventDefault();
    }
  
    let box =  document.querySelector(".confirm-box")
    if  (box.classList.contains("d-none")){ 
        box.classList.remove('d-none')
    }else{
        box.classList.add('d-none')
    }
   


}
document.querySelector('#btn-delete').addEventListener('click',() => {
    fetch("http://localhost:8080/perguntas/"+id,
    {
    method: "DELETE"
    })
    .then(function(res) {
        if (res.ok){
            showAlert("success","Sucesso!","Pergunta deletada com sucesso!");
            showBox();
            
                setTimeout(function() {  document.location.reload(true)}, 600);
          

        }
    })
    .catch(function(res) {console.log(res)})
});
