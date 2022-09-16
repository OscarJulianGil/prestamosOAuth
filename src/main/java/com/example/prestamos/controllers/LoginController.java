package com.example.prestamos.controllers;


import com.example.prestamos.dto.registroDTO;
import com.example.prestamos.entities.TipoDocumento;
import com.example.prestamos.entities.User;
import com.example.prestamos.services.Response;
import com.example.prestamos.services.TipoDocumentoService;
import com.example.prestamos.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class LoginController {

    private UserService service;
    private TipoDocumentoService docService;

    public LoginController(UserService service,TipoDocumentoService docService){
        this.service = service ;
        this.docService = docService;
    }

    @GetMapping("/")
    public String root(){
        return "login/login";
    }
    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    @GetMapping("registro")
    public String registro(Model tiposdocumento){
        //Cargamos los documentos desde la logica de negocio.
        ArrayList<TipoDocumento> tiposDocumentoDB = this.docService.selectAll();
        //Pasamos la infomación al model de thymeleaf
        tiposdocumento.addAttribute("misdocumentos",tiposDocumentoDB);
        tiposdocumento.addAttribute("texto","Bienvenidos");

        return "login/registro";
    }

    @PostMapping("postlogin")
    public RedirectView postlogin(User data){
        Response response = this.service.loginUser(data);
        if(response.getCode() == 200){
            return new RedirectView("/inicio");
        }
        else{
            return new RedirectView("/error");
        }
    }

    @PostMapping("postregistro")
    public RedirectView postregisto(registroDTO data){

        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            System.out.println("Contraseña no valida");
            return new RedirectView("/error");
        }
        if(!data.getPassword().equals(data.getValidaPassword())){
            System.out.println("Las contraseñas no coinciden.");
            return new RedirectView("/error");
        }

        User user = new User();

        //Mapping
        user.setCorreoElectronico(data.getCorreoElectronico());
        user.setPassword(data.getPassword());
        user.setApellidos(data.getApellidos());
        user.setNombres(data.getNombres());
        user.setNumeroDocumento(data.getNumeroDocumento());
        user.setTipoDocumento(data.getTipoDocumento());

        Response response = this.service.createUser(user);
        System.out.println(response.getMessage());
        if(response.getCode() == 200){
            return new RedirectView("/login");
        }
        else{
            return new RedirectView("/error");
        }
    }

    @GetMapping("error")
    public String error(){
        return "login/error";
    }

}
