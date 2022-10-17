package arsenal.com.projeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

     
}
