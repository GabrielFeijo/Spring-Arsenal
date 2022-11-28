var tag = document.createElement('script');
var firstScriptTag = document.getElementsByTagName('script')[0];

// O container do video
const playerDiv = document.querySelector('#iframe');

// inicializando o player
var player;
var videoDuration = 0;
var videotime = 0;

// Adicionando Youtube iframe API
tag.src = 'https://www.youtube.com/iframe_api';
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// Método chamado automaticamente pela api do youtube
function onYouTubeIframeAPIReady() {
    player = new YT.Player('iframe', {
        videoId: $(playerDiv).attr('data-video-url'),
        events: {

            'onStateChange': onStateChange
        },
        playerVars: {
            rel: 0,
            showinfo: 0     
        }
    });
}

// Método chamado nos eventos do player
function onStateChange(event) {
    // obtendo a duração do video, em segundos
    if (event.data == 0) {
        const playerDiv = document.querySelector('#iframe');
        const videId = $(playerDiv).attr('data-video-id');
        const userid = $(playerDiv).attr('data-usuario-id');
        const trilhaid = $(playerDiv).attr('data-trilha-id');
        document.querySelector('.button-check').classList.remove('d-none');

        fetch("http://localhost:8080/salvar-assistido",
        {
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            method: "POST",
            body: JSON.stringify({
                "usuario_id": userid,
                "trilha_idtrilha": trilhaid,
                "video_idvideo": videId
               
            })
        })
        .then(res =>  console.log('Responde:', res))
        .catch(err => console.log('Error, with message:', err));
    }
 
}



