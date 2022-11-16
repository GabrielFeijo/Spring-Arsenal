package arsenal.com.projeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@GetMapping("/")
    public String inicio() {
        return "index";
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

    @RequestMapping(value = "/contato", method = RequestMethod.GET)
    public String contact(){
        return "contact-page/index";
    }

    @RequestMapping(value = "/perguntas-frequentes", method = RequestMethod.GET)
    public String faq(){
        return "forum-page/index";
    }

    
    @RequestMapping(value = "/questionario", method = RequestMethod.GET)
    public String form(){
        return "form/index";
    }


     
}
