package arsenal.com.projeto.models;

public class QueryDTO {
	
	private Integer userid;
	private String nome;
	private String telefone;
	private String email;
	private Integer admin;
	private String nivel;

	public QueryDTO( Integer userid,String nome,String telefone, String email, Integer admin, String nivel) {
		this.userid = userid;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.admin = admin;
		this.nivel = nivel;
	}	
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
}
