package com.votacao.pauta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buscar-voto")
public class VotoController {
    @GetMapping ("/voto")
    public String buscarVoto (){
        return "buscar voto";
    }
}
