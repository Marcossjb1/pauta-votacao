package com.votacao.pauta.service;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario inserirUsuario(Usuario usuario) {
       return usuarioRepository.save(usuario);
    }
}
