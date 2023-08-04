package com.votacao.pauta.service;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario inserirUsuario(Usuario usuario) {
        if (usuario.getNome().isEmpty()) {
            throw new RuntimeException("Inválido, por favor digite um nome válido!");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new RuntimeException("Este usuário não existe na base de dados");
    }

    public void deletarUsuario(Long id) {
        Optional<Usuario> deletarIdUsuario = usuarioRepository.findById(id);
        if (deletarIdUsuario.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado ou já deletado!");
        }
    }
}
