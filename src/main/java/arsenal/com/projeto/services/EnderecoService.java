package arsenal.com.projeto.services;

import arsenal.com.projeto.models.Endereco;
import arsenal.com.projeto.repository.IEndereco;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EnderecoService {
    private IEndereco add;

    public EnderecoService(IEndereco add) {
        this.add = add;
    }
    
    
    public Endereco getEndereco(Integer id) {
    	Endereco endereco = add.getReferenceById(id);
        return (endereco);
    }
    

    public List<Endereco> listaEnderecos() {
        List<Endereco> lista = add.findAll();
        return lista;
    }

    public Endereco novoEndereco(Endereco usersAddress){
        Endereco novoEndereco = add.save(usersAddress);
        return (novoEndereco);
    }

    public Endereco atualizarEndereco(Endereco usersAddress){
        Endereco novoEndereco = add.save(usersAddress);
        return (novoEndereco);
    }

    public Boolean deletarEndereco(Integer id){
        add.deleteById(id);
        return true;
    }


}
