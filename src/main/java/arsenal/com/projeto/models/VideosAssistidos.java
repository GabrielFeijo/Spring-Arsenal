package arsenal.com.projeto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="videos_assistidos")
public class VideosAssistidos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;	

		
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="video_idvideo")
	private Video video;
	
	@ManyToOne
    @JoinColumn(name="trilha_idtrilha", nullable=false)
	private Trilha trilha;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUsuario() {
		return usuario.getUserid();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getVideo() {
		return video.getIdvideo();
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Integer getTrilha() {
		return trilha.getIdtrilha();
	}

	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}	 
	
	
	
	
	
}

