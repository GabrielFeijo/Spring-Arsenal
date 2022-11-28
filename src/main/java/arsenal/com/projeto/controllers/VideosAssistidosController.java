package arsenal.com.projeto.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import arsenal.com.projeto.services.VideosAssistidosService;

@RestController
@CrossOrigin("*")

public class VideosAssistidosController {
	
	private VideosAssistidosService videosAssistidosService;
	
	public VideosAssistidosController(VideosAssistidosService videosAssistidosService) {
        this.videosAssistidosService =  videosAssistidosService;
	}
	 
	@PostMapping("/salvar-assistido")
    public ResponseEntity<?> salvarAssistido(@RequestBody Map<String, String> Info){	
	  if (Info.get("usuario_id") != null && Info.get("trilha_idtrilha") != null && Info.get("video_idvideo") != null) {
		  if (videosAssistidosService.buscarAssistidos(Info.get("usuario_id"), Info.get("video_idvideo")).isEmpty()) {
			  return ResponseEntity.status(201).body(videosAssistidosService.salvarRespostas(Info.get("usuario_id"),Info.get("trilha_idtrilha"),Info.get("video_idvideo")));
		  }else {
			  return ResponseEntity.status(400).body("Video já assistido!");
		  }
		  
	  }else {
		  return ResponseEntity.status(400).body("Erro! Verifique se os campos não estão nulos!");
	  }
    }
	 
	
	@GetMapping("/buscar-assistido")
    public ResponseEntity<?> buscarAssistido(@RequestBody Map<String, String> Info){	
	  if (Info.get("usuario_id") != null && Info.get("trilha_idtrilha") != null ) {
		  return ResponseEntity.status(201).body(videosAssistidosService.listaVideosByTrilha(Info.get("usuario_id"),Info.get("trilha_idtrilha")));
	  }else {
		  return ResponseEntity.status(400).body("Erro! Verifique se os campos não estão nulos!");
	  }
    }
	
	 
	 

}
