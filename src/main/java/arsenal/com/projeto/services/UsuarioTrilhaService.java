package arsenal.com.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arsenal.com.projeto.models.UsuarioTrilha;
import arsenal.com.projeto.repository.IUsuarioTrilha;

@Service
public class UsuarioTrilhaService {
	
	
	  @Autowired
	  IUsuarioTrilha rep; 
	 
    
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
	 
	 public List<UsuarioTrilha> findByUserId(Integer usuario_id){
		 List<UsuarioTrilha> list = rep.findByUserId(usuario_id);
		 return list;
	 }
	
	 
	
	  
	
	
	  
}
