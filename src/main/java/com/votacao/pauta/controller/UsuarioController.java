package com.votacao.pauta.controller;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscarUsuario(id);
    }

    @PostMapping("/inserir")
    public Usuario inserirUsuario(@RequestBody Usuario usuario){
        return usuarioService.inserirUsuario(usuario);
    }
}
