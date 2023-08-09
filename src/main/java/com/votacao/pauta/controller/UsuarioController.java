package com.votacao.pauta.controller;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioServiceImpl.buscarUsuario(id);
    }

    @PostMapping("/inserir")
    public Usuario inserirUsuario(@RequestBody Usuario usuario) {
        return usuarioServiceImpl.inserirUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServiceImpl.deletarUsuario(id);
    }

    @GetMapping("/listar-usuarios")
    public List<Usuario> listarTodosUsuarios() {
        return usuarioServiceImpl.listarTodosUsuarios();
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioServiceImpl.atualizarUsuario(id, usuario);
    }
}
