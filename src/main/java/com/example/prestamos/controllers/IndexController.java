package com.example.prestamos.controllers;


import com.example.prestamos.entities.User;
import com.example.prestamos.services.Response;
import com.example.prestamos.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("index")
public class IndexController {

    //Se coloca una propiedad del Tipo UserSevice, para poder trabajar con la logica de negocio de la aplicacion
    private UserService userService;
    //Por medio de la inyección de dependencias, se inicializa el sevicio.
    public IndexController(UserService service){
        this.userService = service;
    }

    @GetMapping("getusuarios")
    public ArrayList<User> getUsuarios(){
        return this.userService.selectAll();
    }

    @RequestMapping("getuser/{id}")
    public User getusuario(@PathVariable int id){
        return this.userService.selectById(id);
    }

    @PostMapping("create")
    public Response createuser(@RequestBody User request){
         return this.userService.createUser(request);
    }

    @DeleteMapping("delete/{id}")
    public Response deleteUsuario(@PathVariable int id){
        return this.userService.deleteUserById(id);
    }

    @PutMapping("update")
    public Response updateUser(@RequestBody User request){
         return   this.userService.updateUser(request);
    }

    @PostMapping("auth")
    public Response auth(@RequestBody User request){
        return this.userService.loginUser(request);
    }


    @PatchMapping("update")
    public Response otherUpdate(@RequestBody User request){
        return   this.userService.updateUser(request);
    }
}
