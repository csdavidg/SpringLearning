package co.com.david.springbootdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        if (principal != null) {
            redirectAttributes.addFlashAttribute("info", "Usuario con sesion iniciada");
            return "redirect:/";
        }
        return "login";
    }

}
