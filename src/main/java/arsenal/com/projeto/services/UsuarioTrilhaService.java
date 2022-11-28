package arsenal.com.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arsenal.com.projeto.models.UsuarioTrilha;
import arsenal.com.projeto.repository.IUsuarioTrilha;
import arsenal.com.projeto.repository.InsertTrilha;



@Service
public class UsuarioTrilhaService {
	
	
	  @Autowired
	  IUsuarioTrilha rep; 
	  
	  @Autowired 
	  InsertTrilha insertTrilha;
    
    public UsuarioTrilha findById(Integer id){
        UsuarioTrilha newUser = rep.getReferenceById(id);
        return (newUser);
    }

	  public List<UsuarioTrilha> listaModulos() {
        List<UsuarioTrilha> lista = rep.findAll();
        return lista;
    }
	  
	 public UsuarioTrilha novoModulo(UsuarioTrilha usuarioTrilha) {
		 UsuarioTrilha newModuloTrilha = rep.save(usuarioTrilha);
		 return (newModuloTrilha);
	 }
	 
	 public Boolean inserir(Integer trilhaid, Integer usuario_id ) {
		 insertTrilha.insertWithQuery(trilhaid, usuario_id);
		 return true;
	 }
	 
	 public Boolean atualizar(Integer trilhaid, Integer usuario_id ) {
		 insertTrilha.updateWithQuery(trilhaid, usuario_id);
		 return true;
	 }
	 
	 public List<UsuarioTrilha> findByUserId(Integer usuario_id){
		 List<UsuarioTrilha> list = rep.findByUserId(usuario_id);
		 return list;
	 }
	
	 
	
	  
	
	
	  
}
