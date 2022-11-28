package arsenal.com.projeto.controllers;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import arsenal.com.projeto.models.Video;
import arsenal.com.projeto.services.VideoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/videos")
public class VideoController {

	private VideoService videoService;
	
	 public VideoController(VideoService videoService) {
	        this.videoService =  videoService;
	 }
	 
	  @GetMapping
	    public ResponseEntity<List<Video>> listaVideos() {
	        return ResponseEntity.status(200).body(videoService.listaVideo());
	   }
	 
	  @DeleteMapping("/{id}")
	    public ResponseEntity<?> deletarVideo(@PathVariable("id") Integer id){
		  	videoService.deletarVideo(id);
	    	return ResponseEntity.status(204).build();
	    }
	 
   
}
