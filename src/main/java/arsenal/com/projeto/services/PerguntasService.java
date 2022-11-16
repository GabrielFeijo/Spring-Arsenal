package arsenal.com.projeto.services;

import arsenal.com.projeto.models.Perguntas;
import arsenal.com.projeto.repository.IPerguntas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntasService {

    private IPerguntas qrp;

    public PerguntasService(IPerguntas qrp) {
        this.qrp = qrp;
    }

    public List<Perguntas> listaPerguntas() {
        List<Perguntas> lista = qrp.findAll();
        return lista;
    }
    public Optional<Perguntas> getById(Integer id){
        Optional<Perguntas> perguntas = qrp.findById(id);
        return (perguntas);
    }

    public Perguntas addPergunta(Perguntas perguntas){
        Perguntas newQuestion = qrp.save(perguntas);
        return (newQuestion);
    }


    public Perguntas atualizarPergunta(Perguntas perguntas){
        Perguntas newQuestion = qrp.save(perguntas);
        return (newQuestion);
    }

    public Boolean deletarPergunta(Integer id){
        qrp.deleteById(id);
        return true;
    }
}
