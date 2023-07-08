package com.votacao.pauta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // É uma anotação que vai dizer que a classe vai lidar com requisições http
@RequestMapping("/buscar-pauta") // Uma forma de mapear URL's de requisições HTTP para métodos específicos em uma controller
public class PautaController {
    @GetMapping ("/buscar")
    public String buscarPauta (){
        return "buscar pauta";
    }
}
