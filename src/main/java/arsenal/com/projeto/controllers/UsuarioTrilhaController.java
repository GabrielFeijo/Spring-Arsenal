package arsenal.com.projeto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
	
	
	
}
