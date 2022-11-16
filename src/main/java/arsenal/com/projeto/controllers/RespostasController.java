package arsenal.com.projeto.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import arsenal.com.projeto.services.RespostaService;


@RestController
@CrossOrigin("*")
public class RespostasController {
	
	@Autowired
	RespostaService respostaService;
	
	@PostMapping("/enviar-resposta")
    public ResponseEntity<?> novoModulo(@RequestBody Map<String, String> Respostas){	
		  if (Respostas.get("usuario_id") != null && Respostas.get("pergunta_id") != null && Respostas.get("resposta") != null) {
			  return ResponseEntity.status(201).body(respostaService.salvarRespostas(Respostas.get("usuario_id"),Respostas.get("pergunta_id"),Respostas.get("resposta")));
		  }else {
			  return ResponseEntity.status(400).body("Erro! Verifique se os campos não estão nulos!");
		  }
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
