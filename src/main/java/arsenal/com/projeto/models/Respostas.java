package arsenal.com.projeto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "respostas")
public class Respostas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idresposta")
    private Integer idresposta;
	
 	@ManyToOne
    @MapsId("id")
    @JoinColumn(name = "usuario_id")
    Usuario usuario;

    @ManyToOne
    @MapsId("idpergunta")
    @JoinColumn(name = "pergunta_id")
    Perguntas perguntas;

   @NotBlank(message = "A resposta é obrigatória!")
   @Column(name="resposta",length = 255,nullable = false)
   private String resposta;  
	   	
	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}  	   
	  
}
