package com.votacao.pauta.service.impl;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.repository.UsuarioRepository;
import com.votacao.pauta.service.UsuarioService;
import com.votacao.pauta.validation.UsuarioValidator;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioValidator usuarioValidator;

    @Override
    public Usuario inserirUsuario(Usuario usuario) {
        if (!usuario.getNome().isEmpty()) {
            usuarioValidator.validarNome(usuario.getNome());
            usuarioValidator.validarNomeExistente(usuario.getNome());
            return usuarioRepository.save(usuario);
        }
        throw new BadRequestException("O nome não pode ser vazio.");
    }

    @Override
    public Usuario buscarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new ObjectNotFoundException(id, Usuario.class.getSimpleName());
    }

    @Override
    public void deletarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id, Usuario.class.getSimpleName());
        }
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
        Optional<Usuario> atualizarUsuarioPorId = usuarioRepository.findById(id);
        if (atualizarUsuarioPorId.isPresent()) {
            Usuario usuario = atualizarUsuarioPorId.get();
            usuario.setNome(novoUsuario.getNome());
            return usuarioRepository.save(usuario);
        } else {
            throw new ObjectNotFoundException(id, Usuario.class.getSimpleName());
        }
    }
}

