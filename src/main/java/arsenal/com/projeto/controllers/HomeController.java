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
     
}
