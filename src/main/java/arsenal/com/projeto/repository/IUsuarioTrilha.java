package arsenal.com.projeto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import arsenal.com.projeto.models.UsuarioTrilha;


@Repository
public interface IUsuarioTrilha extends JpaRepository<UsuarioTrilha, Integer>{
	
	   boolean existsById(Integer usuario_id);
	   
	   @Query(value = "SELECT * FROM usuario_trilha us WHERE us.usuario_id = ?1",nativeQuery = true)
	   List<UsuarioTrilha> findByUserId(Integer usuario_id);
	   
	   

	
	
}
