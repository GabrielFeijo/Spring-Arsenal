package arsenal.com.projeto.models;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "trilha")
public class Trilha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtrilha")
    private Integer idtrilha;

    @NotBlank(message = "O nível do usuário é obrigatório")
    @Column(name = "nivel",length = 255,nullable = false)
    private String nivel;

    @Column(name = "modulo",length = 100,nullable = true)
    private String modulo;
    
    @OneToMany(mappedBy="trilha",cascade = CascadeType.ALL)
    private Set<Video> video;    

    @OneToMany(mappedBy = "trilhas")
    Set<UsuarioTrilha> usuarioTrilha;
    
	public Integer getIdtrilha() {
		return idtrilha;
	}

	public void setIdtrilha(Integer idtrilha) {
		this.idtrilha = idtrilha;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
    
}
