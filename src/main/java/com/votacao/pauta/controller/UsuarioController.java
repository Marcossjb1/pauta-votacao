package com.votacao.pauta.controller;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public String buscarUsuario() {
        return null;
    }

    @PostMapping("/inserir")
    public Usuario inserirUsuario(@RequestBody Usuario usuario){
        return usuarioService.inserirUsuario(usuario);
    }
}
