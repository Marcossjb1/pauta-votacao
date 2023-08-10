package com.votacao.pauta.service;

import com.votacao.pauta.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario inserirUsuario(Usuario usuario);
    Usuario buscarUsuario(Long id);
    void deletarUsuario(Long id);
    List<Usuario> listarTodosUsuarios();
    Usuario atualizarUsuario(Long id, Usuario novoUsuario);
}
