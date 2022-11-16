package arsenal.com.projeto.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;



@Repository
public class IRespostas{

	
	  @PersistenceContext
	    private EntityManager entityManager;

	   @Transactional
	   public void salvarResposta(Integer usuario_id, Integer pergunta_id, String resposta) {
	       entityManager.createNativeQuery("INSERT INTO respostas (usuario_id, pergunta_id,resposta) VALUES (?,?,?)")
	         .setParameter(1, usuario_id)
	         .setParameter(2, pergunta_id)
	         .setParameter(3, resposta)
	         .executeUpdate();
	   }
}
