package com.votacao.pauta.controller;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.PautaService;
import com.votacao.pauta.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public String buscarUsuario() {
        return null;
    }

    @PostMapping
    public Usuario inserirUsuario(@RequestBody Usuario usuario){
        return usuarioService.inserirUsuario(usuario);
    }
}
