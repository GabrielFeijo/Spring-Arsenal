package arsenal.com.projeto.controllers;

import arsenal.com.projeto.models.Trilha;
import arsenal.com.projeto.services.TrilhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
