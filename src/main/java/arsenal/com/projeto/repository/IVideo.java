package arsenal.com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arsenal.com.projeto.models.Video;

@Repository
public interface IVideo extends JpaRepository<Video, Integer> {

	
}
