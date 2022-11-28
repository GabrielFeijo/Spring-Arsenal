package arsenal.com.projeto.services;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import arsenal.com.projeto.models.QueryDTO;
import arsenal.com.projeto.models.TrilhasCliente;
import arsenal.com.projeto.models.Usuario;
import arsenal.com.projeto.models.UsuarioVideo;
import arsenal.com.projeto.repository.IUsuario;

@Service
public class UsuarioService {
    private IUsuario repository;
    private PasswordEncoder passwordEncoder;
    

    @Autowired
    private EmailService emailService;

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
    public boolean existsByCPF(String CPF){
        return  repository.existsByCPF(CPF);
    }
    public UsuarioService(IUsuario repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> listaUsuarios() {
        List<Usuario> lista = repository.findAll();
        return lista;
    }
    
    public Optional<Usuario> getUser(Integer id) {
    	Optional<Usuario> newUser = repository.findById(id);
        return (newUser);
    }
    
    
	public List<QueryDTO> tabelaUsuarios() {
		List<QueryDTO> list = repository.fetchEmpDeptDataLeftJoin();
		return list;
	}
	
	public TrilhasCliente detalhesUsuario(Integer userid) {
		TrilhasCliente detalheUser = repository.obterDetalhes(userid);
		return detalheUser;
	}
	
	public List<UsuarioVideo> getVideo(Integer id) {
		List<UsuarioVideo> usuarioVideo = repository.trilhaUrl(id);
		return usuarioVideo;
	}
    
    
    public Usuario createUser(Usuario usuario){
        String enconder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(enconder);
        Usuario newUser = repository.save(usuario);
        return (newUser);
    }


    public Usuario updateUser(Usuario usuario){
        String enconder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(enconder);
        Usuario newUser = repository.save(usuario);
        return (newUser);
    }
    public Usuario atualizar(Usuario usuario){
        Usuario newUser = repository.save(usuario);
        return (newUser);
    }

    public Boolean deleteUser(Integer id){
        repository.deleteById(id);
        return true;
    }

    public Usuario findUser(String email){
        Usuario newUser = repository.findByEmail(email);
        return (newUser);
    }

    public Usuario findById(Integer id){
        Usuario newUser = repository.getReferenceById(id);
        return (newUser);
    }

    public Boolean validatePassword(String password,String pass) {
        boolean valid = passwordEncoder.matches(pass,password);
        return valid;
    }
    
    
    // Recuperar Senha por Codigo
    public ResponseEntity<?> solicitarCodigo(String email) {
        Usuario usuario = repository.findByEmail(email);
        
        if (usuario != null ) {
        	Date diferenca;
        	if (usuario.getDataCodigo() != null) {
        		diferenca = new Date(new Date().getTime() - usuario.getDataCodigo().getTime());
        	}else {
        		diferenca = new Date(new Date().getTime());
        	}       
			if (diferenca.getTime() / 1000 >= 900) {
        		 usuario.setCodigoRecuperacao(getCodigoRecuperacaoSenha(usuario.getUserid()));
                 usuario.setDataCodigo(new Date());
                 repository.saveAndFlush(usuario);
                 
                 Map<String, Object> proprMap = new HashMap<>();
    	           proprMap.put("nome", usuario.getNome());
    	           proprMap.put("code",usuario.getCodigoRecuperacao() );
    	           emailService.enviarEmailTemplate(usuario.getEmail(), "Arsenal - Redefinição de Senha", proprMap);                
                      
                 return  ResponseEntity.status(200).body("Email enviado com sucesso!");
        	}else {
        		return  ResponseEntity.status(400).body("Você já possui um código em vigor! Aguarde 15 minutos para gerar outro código!");
        	}
        	
        	
        }else {
        	 return ResponseEntity.status(404).body("Usuário não encontrado!");
        }
       
    }
    
    private String getCodigoRecuperacaoSenha(Integer id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return format.format(new Date()) + id ;
    }
    
    
    public ResponseEntity<?> alterarSenha(Usuario usuario) {
        Usuario usuarioBanco = repository.findByCodigoRecuperacao(usuario.getCodigoRecuperacao());
        if (usuarioBanco != null) {
            Date diferenca = new Date(new Date().getTime() - usuarioBanco.getDataCodigo().getTime());
            // 15 Minutos
            if (diferenca.getTime() / 1000 < 900) {
                          	
            	 String enconder = this.passwordEncoder.encode(usuario.getSenha());
            	 usuarioBanco.setSenha(enconder);
          
                usuarioBanco.setCodigoRecuperacao(null);
                usuarioBanco.setDataCodigo(null);
                repository.saveAndFlush(usuarioBanco);
                return ResponseEntity.status(200).body("Senha alterada com sucesso!");
            } else {
                return ResponseEntity.status(400).body("Tempo expirado, solicite um novo código");
            }
        } else {
            return ResponseEntity.status(404).body("Email ou código não encontrado!");
        }
    }
    
}
