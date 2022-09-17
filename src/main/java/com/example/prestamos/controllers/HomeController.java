package com.example.prestamos.controllers;


import com.example.prestamos.entities.User;
import com.example.prestamos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private UserService service;

    @GetMapping("inicio")
    public String inicio(Model model,@AuthenticationPrincipal OidcUser principal){
        if(principal != null) {
            Map<String, Object> mapa = principal.getClaims();
            User user = this.service.findByUsername((String)mapa.get("email"));
            model.addAttribute("autenticado", user);
            System.out.println(mapa);
        }
        else {
            model.addAttribute("autenticado", seguridad());
        }
        return "home/inicio";
    }

}
