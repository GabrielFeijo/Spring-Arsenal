package arsenal.com.projeto.repository;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;




@Repository
public class VideosAssistidosRepository {

	@PersistenceContext
    private EntityManager entityManager;

   @Transactional
   public void salvarAssistido(Integer usuario_id, Integer trilha_idtrilha, Integer video_idvideo) {
       entityManager.createNativeQuery("INSERT INTO videos_assistidos (usuario_id, trilha_idtrilha,video_idvideo) VALUES (?,?,?)")
         .setParameter(1, usuario_id)
         .setParameter(2, trilha_idtrilha)
         .setParameter(3, video_idvideo)
         .executeUpdate();
   }
   
   @Transactional
   public void deletarAssistido(Integer usuario_id) {
       entityManager.createNativeQuery("DELETE FROM videos_assistidos WHERE usuario_id=?1")
         .setParameter(1, usuario_id)      
         .executeUpdate();
   }

	
}
