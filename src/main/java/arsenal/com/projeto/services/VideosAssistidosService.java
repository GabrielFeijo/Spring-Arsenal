package arsenal.com.projeto.services;



import java.util.List;

import org.springframework.stereotype.Service;

import arsenal.com.projeto.models.VideosAssistidos;
import arsenal.com.projeto.repository.IVideosAssistidos;
import arsenal.com.projeto.repository.VideosAssistidosRepository;

@Service
public class VideosAssistidosService {
	
	private VideosAssistidosRepository repository;
	private IVideosAssistidos repo;
	
	   public VideosAssistidosService(VideosAssistidosRepository repository,IVideosAssistidos repo) {
	        this.repository = repository;	
	        this.repo = repo;
	    }
	   
	   public List<VideosAssistidos> buscarAssistidos(String usuario_id,String video_id) {
		   Integer u_id = Integer.parseInt(usuario_id);			
		   Integer idvideo = Integer.parseInt(video_id);
		   List<VideosAssistidos> video = repo.findByTrilha(  u_id, idvideo);
		   return (video);
		   
	   }
	
	   
	   public String salvarRespostas(String usuario_id, String trilha_idtrilha, String video_idvideo) {		 
			  Integer u_id = Integer.parseInt(usuario_id);
			  Integer idtrilha = Integer.parseInt(trilha_idtrilha);
			  Integer idvideo = Integer.parseInt(video_idvideo);
			  repository.salvarAssistido(u_id,idtrilha,idvideo);
			  return ("Video salvo com sucesso!");			  
	  }
	   
	   public List<VideosAssistidos> listaVideosByTrilha(String usuario_id,String trilha_idtrilha ){
		   
			  Integer id_usuario = Integer.parseInt(usuario_id);
			  Integer id_trilha = Integer.parseInt(trilha_idtrilha);
		   List<VideosAssistidos> videosAssistidos = repo.findByTrilha(id_usuario,id_trilha);		   
		   return (videosAssistidos);
	   }

}
