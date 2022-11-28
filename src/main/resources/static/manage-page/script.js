
document.querySelector('#cancel').addEventListener('click', showBox);


function showBox(id) {
    let box = document.querySelector(".confirm-box")
    if (box.classList.contains("d-none")) {
        box.classList.remove('d-none')

        document.querySelector('#btn-delete').onclick =
            function event() {
                deleteUser(id)
            };
    } else {
        box.classList.add('d-none');
    }
}

function deleteUser(id) {
    location.href = "/gerenciar-usuarios/remover-user/" + id;
}

const INPUT_BUSCA = document.getElementById('search');
const TABELA = document.getElementById('tbody');
INPUT_BUSCA.addEventListener('keyup', () => {
    let expressao = INPUT_BUSCA.value.toLowerCase();

    if (expressao.length === 1) {
        return;
    }

    let linhas = TABELA.getElementsByTagName('tr');

    for (let posicao in linhas) {
        if (true === isNaN(posicao)) {
            continue;
        }

        let conteudoDaLinha = linhas[posicao].innerHTML.toLowerCase();

        if (true === conteudoDaLinha.includes(expressao)) {
            linhas[posicao].style.display = '';
        } else {
            linhas[posicao].style.display = 'none';
        }
    }
});


