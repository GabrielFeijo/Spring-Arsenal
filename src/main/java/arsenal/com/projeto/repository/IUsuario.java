package arsenal.com.projeto.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import arsenal.com.projeto.models.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByCPF(String CPF); 

     Usuario findByEmail(String email);
     
}
