package arsenal.com.projeto.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@CrossOrigin("*")
@Controller
public class HomeController {
	
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/contato", method = RequestMethod.GET)
    public String contact(){
        return "contact-page/index";
    }

    @RequestMapping(value = "/perguntas-frequentes", method = RequestMethod.GET)
    public String faq(){
        return "forum-page/index";
    }

    @RequestMapping(value = "sessao/questionario", method = RequestMethod.GET)
    public String form(){
        return "form/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView main(){
        ModelAndView mw = new ModelAndView("login-page/index");
        return mw;
    }
    
    @RequestMapping(value = "/registrar", method = RequestMethod.GET)
    public String register(){
        return "register-page/index";
    }

    @RequestMapping(value = "/esqueceu-a-senha", method = RequestMethod.GET)
    public String reset(){
        return "reset-password/index";
    }
    
    @RequestMapping(value = "/redefinir-senha", method = RequestMethod.GET)
    public String redefinir(){
        return "new-password/index";
    }

}
