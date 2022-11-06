package arsenal.com.projeto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import arsenal.com.projeto.models.Video;
import arsenal.com.projeto.repository.IVideo;

@Service
public class VideoService {
	private IVideo rvv;
	
	public VideoService (IVideo rvv) {
		this.rvv = rvv;
	}
	
	public List<Video> listaVideo(){
		List<Video> lista = rvv.findAll();
		return lista;
	}
	
	public Video novoVideo(Video video) {
		Video novoVideo = rvv.save(video);
		return (novoVideo);
	}
	
	public Video atualizarVideo(Video video) {
		Video novoVideo = rvv.save(video);
		return (novoVideo);
	}
	
	public Boolean deletarVideo(Integer id) {
		rvv.deleteById(id);
		return true;
	}
		
		
		

}
