package arsenal.com.projeto.controllers;

import arsenal.com.projeto.models.Trilha;
import arsenal.com.projeto.services.TrilhaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class TrilhaController {
    private TrilhaService trilhaService;

    public TrilhaController(TrilhaService trilhaService ) {
        this.trilhaService = trilhaService;
    }

    
    @RequestMapping(value = "/trilhas", method = RequestMethod.GET)
    public ResponseEntity<List<Trilha>> UsersTrailList() {
        return ResponseEntity.status(200).body(trilhaService.listaTrilhas());
    }

    @PostMapping("/trilhas")
    public ResponseEntity<Trilha> addQuestion(@Valid @RequestBody Trilha trilha){
        return ResponseEntity.status(201).body(trilhaService.addTrilha(trilha));
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
