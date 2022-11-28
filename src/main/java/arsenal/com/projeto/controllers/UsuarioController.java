package arsenal.com.projeto.controllers;


import arsenal.com.projeto.models.Endereco;
import arsenal.com.projeto.models.QueryDTO;
import arsenal.com.projeto.models.Trilha;
import arsenal.com.projeto.models.TrilhasCliente;
import arsenal.com.projeto.models.Usuario;
import arsenal.com.projeto.models.UsuarioVideo;
import arsenal.com.projeto.models.Video;
import arsenal.com.projeto.repository.ITrilha;
import arsenal.com.projeto.repository.IVideo;
import arsenal.com.projeto.repository.VideosAssistidosRepository;
import arsenal.com.projeto.services.EmailService;
import arsenal.com.projeto.services.EnderecoService;
import arsenal.com.projeto.services.TrilhaService;
import arsenal.com.projeto.services.UsuarioService;
import arsenal.com.projeto.services.UsuarioTrilhaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	IVideo repoIVideo;
	
	@Autowired
	ITrilha repoITrilha;
	
    @Autowired 
    EmailService emailService;
	
	@Autowired
	VideosAssistidosRepository repository;
	
    private UsuarioService usuarioService;
    private UsuarioTrilhaService usuarioTrilhaService;
    private TrilhaService trilhaService;
    private EnderecoService enderecoService;
    private Usuario usuario;

    

    public UsuarioController(UsuarioService usuarioService,TrilhaService trilhaService,UsuarioTrilhaService usuarioTrilhaService, EnderecoService enderecoService ) {
        this.usuarioService =  usuarioService;
        this.trilhaService = trilhaService;
        this.usuarioTrilhaService = usuarioTrilhaService;
        this.enderecoService = enderecoService;
    }



    private void buscarUsuarioLogado(){
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)){
            String email = autenticado.getName();
            usuario = usuarioService.findUser(email);
        }
    }

  

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> userLists() {
        return ResponseEntity.status(200).body(usuarioService.listaUsuarios());
    }
    
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Optional<Usuario>> getUser(@PathVariable("id") Integer id) {
        return ResponseEntity.status(200).body(usuarioService.getUser(id));
    }
    
    
    @GetMapping("/gerenciar-usuario/{id}")
    public ModelAndView editUser(@PathVariable("id") Integer id) {
    	 ModelAndView mw = new ModelAndView("edit-user/index");
    	 
    	 Usuario usuario = usuarioService.findById(id);
    	Endereco endereco = enderecoService.getEndereco(id);
    	 
    	 mw.addObject("usuario",usuario);
    	 mw.addObject("endereco",endereco);
         return mw;
       
    }
    
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Integer id){
    	usuarioService.deleteUser(id);
    	return ResponseEntity.status(204).build();
    }
    
   
    
    @PostMapping("/usuarios")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Usuario usuario){
        if (usuarioService.existsByEmail(usuario.getEmail())){
            return ResponseEntity.status(400).body("Email já está sendo utilizado!");
        }
        if (usuarioService.existsByCPF(usuario.getCPF())){
            return ResponseEntity.status(400).body("CPF já está sendo utilizado!");
        }
	     
        	ResponseEntity<Object> retornoEntity = ResponseEntity.status(201).body(usuarioService.createUser(usuario));      	
          
        return retornoEntity;
    }


    @PutMapping("/usuarios")
    public ResponseEntity<Usuario> updateUser(@Valid @RequestBody Usuario usuario){
        return  ResponseEntity.status(200).body(usuarioService.updateUser(usuario));
    }

  

    @PutMapping("/edit-usuarios")
    public ResponseEntity<Usuario> atualizarUser(@Valid @RequestBody Usuario usuario){
        return  ResponseEntity.status(200).body(usuarioService.atualizar(usuario));
    }

    @RequestMapping(value = "/gerenciar-usuarios/add-nivel/{id}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable("id") Integer id,RedirectAttributes atributes) {
        buscarUsuarioLogado();
        if (usuario.getAdmin() == 5) {   
            Usuario usuario = usuarioService.findById(id);
	        if (usuario.getAdmin() < 5){
	            usuario.setAdmin(usuario.getAdmin()+1);
	            usuarioService.atualizar(usuario);
	            atributes.addFlashAttribute("mensagem","Nível alterado com sucesso!");
	        }else {
	        	atributes.addFlashAttribute("mensagem","Não foi possível alterar o nível!");
	        }
        }else {
        	atributes.addFlashAttribute("mensagem","Você não tem permissão!");
        }
        return new ModelAndView("redirect:/sessao/gerenciar-usuarios");
    }

    @RequestMapping(value = "/gerenciar-usuarios/remover-nivel/{id}", method = RequestMethod.GET)
    public ModelAndView remover(@PathVariable("id") Integer id, RedirectAttributes atributes) {
    	  buscarUsuarioLogado();
       if (usuario.getAdmin() == 5) {    
	        Usuario usuario = usuarioService.findById(id);
	        if (usuario.getAdmin() > 0){
	            usuario.setAdmin(usuario.getAdmin()-1);
	            usuarioService.atualizar(usuario);
	            atributes.addFlashAttribute("mensagem","Nível alterado com sucesso!");
	        }else {
	        	atributes.addFlashAttribute("mensagem","Você não tem permissão!");
	        }
       }else {
    	   atributes.addFlashAttribute("mensagem","Você não tem permissão!");
       }
        return new ModelAndView("redirect:/sessao/gerenciar-usuarios");

   }

    @RequestMapping(value = "/gerenciar-usuarios/remover-user/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") Integer id) {
        usuarioService.deleteUser(id);
        return  new ModelAndView("redirect:/sessao/gerenciar-usuarios");
    }
    
    


    @GetMapping("/sessao/inicio")
    public ModelAndView inicio(){
        buscarUsuarioLogado();

            if (usuario.getAdmin() > 0){
                ModelAndView mw = new ModelAndView("manage/index");
                mw.addObject("usuarioLogado",usuario);
                return mw;
            }else  if (!usuarioTrilhaService.findByUserId(usuario.getUserid()).isEmpty()){
                   ModelAndView mw = new ModelAndView("home-page/index");
             
                   TrilhasCliente detalheUsuario = usuarioService.detalhesUsuario(usuario.getUserid());
                   List<Trilha> trilhas = trilhaService.listaTrilhas();
                   List<UsuarioVideo> video = usuarioService.getVideo(usuario.getUserid());
                   mw.addObject("trilhas",trilhas);
                   mw.addObject("detalheUsuario",detalheUsuario);
                   mw.addObject("usuarioLogado",usuario);
                   mw.addObject("usuarioVideo",video);
                    return mw;
            }else{
                ModelAndView mw = new ModelAndView("form/index");
                mw.addObject("usuarioLogado",usuario);
                return mw;
            }

    }


    @RequestMapping(value = "sessao/logout", method = RequestMethod.GET)
    public ModelAndView logout(){
        ModelAndView mw = new ModelAndView("exit-page/index");
        return mw;
    }

   
    
    @RequestMapping(value = "/sessao/trilhas", method = RequestMethod.GET)
    public ModelAndView principal(){
     	buscarUsuarioLogado();
    // 	Trilha trilha = trilhaService.getTrail(usuario.getUserid());
    	List<UsuarioVideo> video = usuarioService.getVideo(usuario.getUserid());
     	ModelAndView mw = new ModelAndView( "main-page/index");
     	Video videoAtual = null;
     	Integer assistido = 0;
 		  for (UsuarioVideo v: video) {
 			 
     		if (v.getAssistido() == null) {
     			videoAtual = repoIVideo.findByTitulo(v.getTitulo());
     			 break;
     		}	     	   
 		  }
 		  if (videoAtual == null) {
 			 ModelAndView mhome = new ModelAndView("home-page/index");
 		     TrilhasCliente detalheUsuario = usuarioService.detalhesUsuario(usuario.getUserid());
 		    
 		     if (repoITrilha.existsById(detalheUsuario.getTrilhaid()+1)) {
 		    	 mhome.addObject("message",detalheUsuario.getTrilhaAtual());
 		    	 usuarioTrilhaService.atualizar(detalheUsuario.getTrilhaid()+1, usuario.getUserid());
 		     } else {
 		    	 mhome.addObject("message","todas as trilhas disponíveis"); 
 		    	 usuarioTrilhaService.atualizar(1, usuario.getUserid());
 		    	repository.deletarAssistido(usuario.getUserid());
 		     }
 			 
 			detalheUsuario = usuarioService.detalhesUsuario(usuario.getUserid());
             
        
             List<Trilha> trilhas = trilhaService.listaTrilhas();
             List<UsuarioVideo> videos = usuarioService.getVideo(usuario.getUserid());

             mhome.addObject("trilhas",trilhas);
             mhome.addObject("detalheUsuario",detalheUsuario);
             mhome.addObject("usuarioLogado",usuario);
             mhome.addObject("usuarioVideo",videos);
              return mhome;
 			  
 		  }else {
 			 for (UsuarioVideo v: video) {
 	      		if (v.getAssistido() != null) {
 	      			assistido++;
 	      		}	     	   
 	  		  }
 	 		 Double porcentagem = ((double)assistido/video.size())*100;
 	 		String percent = porcentagem.intValue()+"%";
 	 	
 	 		 mw.addObject("usuarioLogado",usuario);
 	 		 mw.addObject("Porcentagem",percent);
 		     mw.addObject("videoAtual", videoAtual);
 		     mw.addObject("usuarioVideo",video);
 	        return mw;
 		  }
 		
    }

    
    
    @RequestMapping(value = "/sessao/gerenciar-usuarios", method = RequestMethod.GET)
    public ModelAndView manage() {
    	buscarUsuarioLogado();
    	if (usuario.getAdmin() > 0) {
    		 ModelAndView mw = new ModelAndView( "manage-page/index");
    	        Iterable<QueryDTO> usuarios = usuarioService.tabelaUsuarios();;
    	        Iterable<Trilha> trilhas = trilhaService.listaTrilhas();
    	        mw.addObject("usuarios",usuarios);
    	        mw.addObject("trilhas",trilhas);
    	        return mw;
    		
    	}else {
    		  return inicio();
    	}
       
    }
    @RequestMapping(value = "sessao/editar-pegunta", method = RequestMethod.GET)
    public ModelAndView edit(){
    	buscarUsuarioLogado();
    	if (usuario.getAdmin() > 0) {
      		ModelAndView mw = new ModelAndView( "edit-page/editar");
      		return mw;
      	}else {
      		 return inicio();
      	}
      
    }

    @RequestMapping(value = "sessao/adicionar-pegunta", method = RequestMethod.GET)
    public ModelAndView add(){
    	buscarUsuarioLogado();
    	if (usuario.getAdmin() > 0) {
    		ModelAndView mw = new ModelAndView( "edit-page/index");
    		return mw;
    	}else {
    		 return inicio();
    	}
    }
    
    
	    @PostMapping("/senha-codigo")
	    public ResponseEntity<?> recuperarCodigo(@RequestBody Usuario usuario){
	       return usuarioService.solicitarCodigo(usuario.getEmail());
	    }
	
	    @PostMapping("/senha-alterar")
	    public ResponseEntity<?> alterarSenha(@RequestBody Usuario usuario){
	       return usuarioService.alterarSenha(usuario);
	    }
	
	    
	    @PostMapping("/enviar-email/{id}")
	    public String enviarEmail(@PathVariable("id") Integer id) {
	    	
	        Usuario usuario = usuarioService.findById(id);	           
	       	Map<String, Object> proprMap = new HashMap<>();
	        proprMap.put("nome", usuario.getNome());         
	        emailService.enviarEmailCadastro(usuario.getEmail(), "Arsenal - Bem Vindo", proprMap);  
        	return "Enviado"; 
	    }
    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
}
