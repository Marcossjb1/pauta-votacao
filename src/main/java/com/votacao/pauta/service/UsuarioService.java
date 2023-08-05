package com.votacao.pauta.service;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
        Optional<Usuario> atualizarUsuarioPorId = usuarioRepository.findById(id);
        if (atualizarUsuarioPorId.isPresent()) {
            Usuario usuario = atualizarUsuarioPorId.get();
            usuario.setNome(novoUsuario.getNome());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("O usuário não existe ou não pode ser atualizado!");
        }
    }
}

