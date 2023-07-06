package com.votacao.pauta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // É uma anotação que vai dizer que a classe vai lidar com requisições http
@RequestMapping("/") // Uma forma de mapear URL's de requisições HTTP para métodos específicos em uma controller
public class HelloWorldController {
    @GetMapping ("/hello")
    public String helloWorld (){
        return "Hello World";
    }
    @GetMapping("/nome")
    public String mostrarNome (){
        return "Marcos";
    }
}
