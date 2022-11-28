package arsenal.com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import arsenal.com.projeto.models.VideosAssistidos;

public interface IVideosAssistidos extends JpaRepository<VideosAssistidos, Integer>{
	
	  @Query(value = "SELECT * FROM videos_assistidos va WHERE va.usuario_id = ?1 and va.video_idvideo = ?2",nativeQuery = true)
	   List<VideosAssistidos> findByTrilha( Integer usuario_id,Integer video_id);

}
