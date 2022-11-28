package arsenal.com.projeto.models;

public class UsuarioVideo {

	
	private Integer userid;
	private String trilha;
	private Integer trilhaid;
	private String url;
	private String titulo;
	private Integer assistido;

	public UsuarioVideo( Integer userid,String trilha,Integer trilhaid,String url,String titulo,Integer assistido) {
		this.userid = userid;
		this.trilha = trilha;
		this.trilhaid = trilhaid;
		this.url = url;
		this.titulo = titulo;
		this.assistido = assistido;
	}

	public Integer getUserid() {
		return userid;
	}
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTrilha() {
		return trilha;
	}

	public void setTrilha(String trilha) {
		this.trilha = trilha;
	}
	

	public Integer getTrilhaid() {
		return trilhaid;
	}

	public void setTrilhaid(Integer trilhaid) {
		this.trilhaid = trilhaid;
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

	public Integer getAssistido() {
		return assistido;
	}

	public void setAssistido(Integer assistido) {
		this.assistido = assistido;
	}
	
	

}
