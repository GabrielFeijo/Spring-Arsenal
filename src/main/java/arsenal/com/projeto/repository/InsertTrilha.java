package arsenal.com.projeto.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class InsertTrilha {

	
	  @PersistenceContext
	    private EntityManager entityManager;

	   @Transactional
	   public void insertWithQuery(Integer trilhaid, Integer usuario_id) {
	       entityManager.createNativeQuery("INSERT INTO usuario_trilha (trilha_id, usuario_id) VALUES (?,?)")
	         .setParameter(1, trilhaid)
	         .setParameter(2, usuario_id)
	   
	         .executeUpdate();
	   }
	   
	   @Transactional
	   public void updateWithQuery(Integer trilhaid, Integer usuario_id) {
	       entityManager.createNativeQuery("UPDATE usuario_trilha SET trilha_id =?1 where usuario_id=?2 ")
	         .setParameter(1, trilhaid)
	         .setParameter(2, usuario_id)
	   
	         .executeUpdate();
	   }
}
