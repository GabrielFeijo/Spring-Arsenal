package arsenal.com.projeto.repository;

import arsenal.com.projeto.models.Trilha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrilha extends JpaRepository<Trilha,Integer> {
	
	   boolean existsByIdtrilha(Integer idtrilha); 


}
