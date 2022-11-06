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

@Entity
@Table(name = "usuario_trilha")
public class UsuarioTrilha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

 	@ManyToOne
    @MapsId("id")
    @JoinColumn(name = "usuario_id")
    Usuario usuario;

    @ManyToOne
    @MapsId("idtrilha")
    @JoinColumn(name = "trilha_id")
    Trilha trilhas;
     



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Trilha getTrilhas() {
		return trilhas;
	}

	public void setTrilhas(Trilha trilhas) {
		this.trilhas = trilhas;
	}



	
	

	
    
	
    
}
