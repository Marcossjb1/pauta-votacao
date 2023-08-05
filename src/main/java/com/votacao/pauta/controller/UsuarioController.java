package com.votacao.pauta.controller;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    public Usuario inserirUsuario(@RequestBody Usuario usuario) {
        return usuarioService.inserirUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }

    @GetMapping("/listar-usuarios")
    public List<Usuario> listarTodosUsuarios() {
       return usuarioService.listarTodosUsuarios();
    }
}
