package arsenal.com.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import arsenal.com.projeto.models.UsuarioTrilha;
import arsenal.com.projeto.services.UsuarioService;
import arsenal.com.projeto.services.UsuarioTrilhaService;


@RestController
@CrossOrigin("*")
public class UsuarioTrilhaController {
	
	
	 @Autowired
	 UsuarioTrilhaService usuarioTrilhaService; 
	 
	 @Autowired
	 UsuarioService usuarioService;
	 
	 
	 
	@GetMapping("/modulos")
    public ResponseEntity<List<UsuarioTrilha>> listaModulos() {
        return ResponseEntity.status(200).body(usuarioTrilhaService.listaModulos());
    }
	
	@PostMapping("/modulos")
    public ResponseEntity<UsuarioTrilha> novoModulo(@RequestBody UsuarioTrilha usuarioTrilha){
        return ResponseEntity.status(201).body(usuarioTrilhaService.novoModulo(usuarioTrilha));
    }
	
	
	@RequestMapping(value="/modulo/{trilha_id}/{usuario_id}", method=RequestMethod.POST)
    public ResponseEntity<?> novoModulo(@PathVariable("trilha_id") Integer trilha_id, @PathVariable("usuario_id") Integer usuario_id ){
        return ResponseEntity.status(204).body(usuarioTrilhaService.inserir(trilha_id, usuario_id));
    }
	
	
	
	
}
