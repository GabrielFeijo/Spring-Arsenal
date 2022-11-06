package arsenal.com.projeto.services;


import arsenal.com.projeto.models.Trilha;
import arsenal.com.projeto.repository.ITrilha;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaService {

    private ITrilha trilha;

    public TrilhaService(ITrilha trilha) {
        this.trilha = trilha;
    }

 
    public List<Trilha> listaTrilhas() {
        List<Trilha> lista = trilha.findAll();
        return lista;
    }

    public Trilha addTrilha(Trilha Trilha){
        Trilha novaTrilha = trilha.save(Trilha);
        return (novaTrilha);
    }

    public Trilha atualizar(Trilha Trilha){
        Trilha newTrail = trilha.save(Trilha);
        return (newTrail);
    }

    public Boolean deletarTrilha(Integer id){
        trilha.deleteById(id);
        return true;
    }
    
}
