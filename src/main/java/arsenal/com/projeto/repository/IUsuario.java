package arsenal.com.projeto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import arsenal.com.projeto.models.QueryDTO;
import arsenal.com.projeto.models.TrilhasCliente;
import arsenal.com.projeto.models.Usuario;
import arsenal.com.projeto.models.UsuarioVideo;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByCPF(String CPF); 

     Usuario findByEmail(String email);
     
     Usuario findByCodigoRecuperacao( String codigoRecuperacao);
     
     
     @Query("SELECT new arsenal.com.projeto.models.QueryDTO(u.id,u.nome,u.telefone,u.email,u.admin,tt.nivel)"
 			+ "from Usuario u LEFT JOIN u.usuarioTrilha ut LEFT JOIN ut.trilhas tt")
 	List<QueryDTO> fetchEmpDeptDataLeftJoin();

     
     @Query("SELECT new arsenal.com.projeto.models.UsuarioVideo(u.id,tt.nivel,tt.idtrilha,v.url,v.titulo,va.id )"
 			+ "from Usuario u LEFT JOIN u.usuarioTrilha ut LEFT JOIN ut.trilhas tt LEFT JOIN tt.video v  LEFT JOIN u.videosAssistidos  va  where u.id =?1  group by v.url" )
     List<UsuarioVideo> ta(Integer id);

     
     @Query("SELECT new arsenal.com.projeto.models.UsuarioVideo(u.id,tt.nivel,tt.idtrilha,v.url,v.titulo,va.usuario.userid )"
 			+ "from Usuario u\r\n"
 			+ "LEFT JOIN u.usuarioTrilha ut \r\n"
 			+ "LEFT JOIN ut.trilhas tt \r\n"
 			+ "LEFT JOIN tt.video v  \r\n"
 			+ "LEFT JOIN v.videosAssistidos  va with va.usuario.userid =?1 \r\n" 
 	
 			
 			+ "where u.id =?1 " 
 			 )
     List<UsuarioVideo> trilhaUrl(Integer id);
     
     
     @Query("SELECT new arsenal.com.projeto.models.TrilhasCliente(u.id,tt.id,tt.nivel)"
  			+ "from Usuario u LEFT JOIN u.usuarioTrilha ut LEFT JOIN ut.trilhas tt where u.id =?1")
     TrilhasCliente obterDetalhes(Integer userid);
     
     
 
     
}

