package arsenal.com.projeto.controllers;


import arsenal.com.projeto.models.Perguntas;

import arsenal.com.projeto.services.PerguntasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/perguntas")
public class PerguntasController {

    private PerguntasService perguntasService;

    public PerguntasController(PerguntasService perguntasService) {
        this.perguntasService =  perguntasService;
    }


    @GetMapping
    public ResponseEntity<List<Perguntas>> listaPerguntas() {
        return ResponseEntity.status(200).body(perguntasService.listaPerguntas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Perguntas>> getById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(perguntasService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Perguntas> addPergunta(@Valid @RequestBody Perguntas perguntas){
        return ResponseEntity.status(201).body(perguntasService.addPergunta(perguntas));
    }

    @PutMapping
    public ResponseEntity<Perguntas> atualizarPergunta(@Valid @RequestBody Perguntas perguntas){
        return  ResponseEntity.status(200).body(perguntasService.atualizarPergunta(perguntas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPergunta(@PathVariable Integer id){
        perguntasService.deletarPergunta(id);
        return ResponseEntity.status(204).build();
    }
}
