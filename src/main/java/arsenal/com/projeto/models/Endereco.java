package arsenal.com.projeto.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name= "endereco")
public class Endereco {
	
	
    @Id
    @Column(name = "usuario_id",length = 11,nullable = false)
    private Integer usuario_id;
    
    @NotBlank(message = "A cidade é obrigatória!")
    @Column(name = "cidade",length = 50,nullable = false)
    private String cidade;
    
    @NotBlank(message = "A UF é obrigatória!")
    @Column(name="UF",length = 2,nullable = false)
    private String UF;
    
    @NotBlank(message = "A rua é obrigatória!")
    @Column(name="rua",length = 255,nullable = false)
    private String rua;

    @Column(name="numero",length = 40,nullable = false)
    private Integer numero;
    
    @NotBlank(message = "O cep é obrigatório!")
    @Column(name = "cep",length = 20,nullable = false)
    private String cep;

    @Column(name = "complemento",length = 50,nullable = true)
    private String complemento;

    @OneToOne
    @MapsId("id")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}    
    
}
