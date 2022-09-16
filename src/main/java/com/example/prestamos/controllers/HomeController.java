package com.example.prestamos.controllers;


import com.example.prestamos.entities.User;
import com.example.prestamos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private UserService service;


    @GetMapping("inicio")
    public String inicio(Model model){
        model.addAttribute("autenticado",seguridad());
        return "home/inicio";
    }

}
