package arsenal.com.projeto.controllers;

import arsenal.com.projeto.models.Endereco;
import arsenal.com.projeto.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/enderecos")
public class EnderecoController {

    private EnderecoService userAddressService;

    public EnderecoController(EnderecoService userAddressService) {
        this.userAddressService =  userAddressService;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listaEnderecos() {
        return ResponseEntity.status(200).body(userAddressService.listaEnderecos());
    }

    @PostMapping
    public ResponseEntity<Endereco> novoEndereco(@RequestBody Endereco usersAddress){
        return ResponseEntity.status(201).body(userAddressService.novoEndereco(usersAddress));
    }

    @PutMapping
    public ResponseEntity<Endereco> atualizarEndereco(@RequestBody Endereco usersAddress){
        return  ResponseEntity.status(200).body(userAddressService.atualizarEndereco(usersAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable Integer id){
        userAddressService.deletarEndereco(id);
        return ResponseEntity.status(204).build();
    }

}
