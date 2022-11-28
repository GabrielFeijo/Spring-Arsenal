package arsenal.com.projeto.models;



public class TrilhasCliente {
	
	private Integer userid;
	private Integer trilhaid;
	private String trilhaAtual;	

	public TrilhasCliente( Integer userid,Integer trilhaid,String trilhaAtual) {
		this.userid = userid;
		this.trilhaid = trilhaid;
		this.trilhaAtual = trilhaAtual;		
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public Integer getTrilhaid() {
		return trilhaid;
	}

	public void setTrilhaid(Integer trilhaid) {
		this.trilhaid = trilhaid;
	}

	public String getTrilhaAtual() {
		return trilhaAtual;
	}

	public void setTrilhaAtual(String trilhaAtual) {
		this.trilhaAtual = trilhaAtual;
	}
	
}
