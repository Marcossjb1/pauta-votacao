package com.votacao.pauta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buscar-usuario")
public class UsuarioController {
    @GetMapping ("usuario")
    public String buscarUsuario (){
        return "buscar usuário";
    }

}
