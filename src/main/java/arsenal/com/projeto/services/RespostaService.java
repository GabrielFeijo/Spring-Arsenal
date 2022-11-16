package arsenal.com.projeto.services;


import org.springframework.stereotype.Service;

import arsenal.com.projeto.repository.IRespostas;


@Service
public class RespostaService {
	
	private IRespostas repositoryIRespostas;
	
	  public RespostaService(IRespostas repositoryIRespostas) {
	        this.repositoryIRespostas = repositoryIRespostas;
	    }

	  
	  public String salvarRespostas(String usuario_id, String pergunta_id, String resposta) {		 
			  Integer u_id = Integer.parseInt(usuario_id);
			  Integer p_id = Integer.parseInt(pergunta_id);
			   repositoryIRespostas.salvarResposta(u_id,p_id,resposta);
			  return ("Respota salva com sucesso!");			  
	  }

}
