package arsenal.com.projeto.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "perguntas")
public class Perguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpergunta")
    private Integer idpergunta;

    @NotBlank(message = "A pergunta é obrigatória!")
    @Column(name = "pergunta",length = 300,nullable = false)
    private String pergunta;

    @Column(name = "alternativas",length = 255,nullable = true)
    private String alternativas;

    @Column(name = "proximapergunta",length = 200,nullable = true)
    private String proximapergunta;
    
    @OneToMany(mappedBy = "perguntas")
    List<Respostas> respostas;

	public Integer getIdpergunta() {
		return idpergunta;
	}

	public void setIdpergunta(Integer idpergunta) {
		this.idpergunta = idpergunta;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(String alternativas) {
		this.alternativas = alternativas;
	}

	public String getProximapergunta() {
		return proximapergunta;
	}

	public void setProximapergunta(String proximapergunta) {
		this.proximapergunta = proximapergunta;
	}
	
}
