package arsenal.com.projeto.controllers;

import arsenal.com.projeto.models.Usuario;
import arsenal.com.projeto.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("*")
public class UsuarioController {

    private UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService ) {
        this.usuarioService =  usuarioService;
     
    }
  

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> UserLists() {
        return ResponseEntity.status(200).body(usuarioService.listaUsuarios());
    }

    
    @PostMapping("/usuarios")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Usuario usuario){
        if (usuarioService.existsByEmail(usuario.getEmail())){
            return ResponseEntity.status(400).body("Conflict: Email is already in use!");
        }
        if (usuarioService.existsByCPF(usuario.getCPF())){
            return ResponseEntity.status(400).body("Conflict: CPF is already in use!");
        }
        return ResponseEntity.status(201).body(usuarioService.createUser(usuario));
    }


    @PutMapping("/usuarios")
    public ResponseEntity<Usuario> updateUser(@Valid @RequestBody Usuario usuario){
        return  ResponseEntity.status(200).body(usuarioService.updateUser(usuario));
    }

    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
    	usuarioService.deleteUser(id);
        return ResponseEntity.status(204).build();
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
