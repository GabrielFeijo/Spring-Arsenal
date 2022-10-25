package arsenal.com.projeto.services;

import arsenal.com.projeto.models.Usuario;
import arsenal.com.projeto.repository.IUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private IUsuario repository;
    private PasswordEncoder passwordEncoder;

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

}
