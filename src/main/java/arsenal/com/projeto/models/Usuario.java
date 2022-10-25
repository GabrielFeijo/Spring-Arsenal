package arsenal.com.projeto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "usuarios")
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer userid;

    @NotBlank(message = "O nome é obrigatório!")
    @Column(name = "nome",length = 255,nullable = false)
    private String nome;
    
    @Email(message = "Insira um Email válido!")
    @NotBlank(message = "O email é obrigatório!")
    @Column(name="email", length = 255,nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória!")
    @Column(name = "senha",length = 255, nullable = false)
    private String senha;
    
    @NotBlank(message = "O telefone é obrigatório!")
    @Column(name = "telefone",length = 50,nullable = false)
    private String telefone;
    
    @NotBlank(message = "O CPF é obrigatório!")
    @Column(name="CPF",length = 20,nullable = false)
    private String CPF;
    
    @NotBlank(message = "A data de nascimento é obrigatória!")
    @Column(name="data_nasc",columnDefinition = "date",nullable = false)
    private String data_nasc;

    @NotBlank(message = "A escolaridade é obrigatória!")
    @Column(name="escolaridade",length = 50,nullable = false)
    private String escolaridade;
    
    @NotBlank(message = "O gênero é obrigatório!")
    @Column(name = "genero",length = 50,nullable = false)
    private String genero;

    @NotNull(message = "O nivel admin é obrigatório!")
    @Column(name = "admin",length = 2,nullable = false)
    private Integer admin;
  
  
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	

	public String getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
   
}
