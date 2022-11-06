package arsenal.com.projeto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name="videos")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idvideo")
	private Integer idvideo;
	
	
	@NotBlank(message = "A URL é obrigatória!")
	@Column(name = "url" , nullable=false)
	private String url;	
	
	@NotBlank(message = "O nome é obrigatório!")
	@Column(name = "titulo" , nullable=false)
	private String titulo;

	
	@ManyToOne
    @JoinColumn(name="trilha_idtrilha", nullable=false)
	 private Trilha trilha;
	

	public Integer getIdvideo() {
		return idvideo;
	}

	public void setIdvideo(Integer idvideo) {
		this.idvideo = idvideo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	


	
	
	
	

}
