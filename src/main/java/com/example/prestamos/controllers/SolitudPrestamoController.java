package com.example.prestamos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("solicitud")
public class SolitudPrestamoController extends BaseController {

    @GetMapping("solicitud")
   public String solicitud(Model data){
       data.addAttribute("titulopagina","Solicitud de prestamos");
       data.addAttribute("autenticado",seguridad());
       return "solicitudprestamo/solicitud";
   }

}
