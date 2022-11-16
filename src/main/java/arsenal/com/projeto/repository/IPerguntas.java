package arsenal.com.projeto.repository;

import arsenal.com.projeto.models.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerguntas extends JpaRepository<Perguntas, Integer> {
}
