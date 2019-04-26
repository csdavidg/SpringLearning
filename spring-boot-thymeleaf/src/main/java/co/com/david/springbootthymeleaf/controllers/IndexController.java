package co.com.david.springbootthymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/hola")
    public String hola(Model model){
        model.addAttribute("mensaje", "Hola Spring boot");
        return "hola";
    }


}
