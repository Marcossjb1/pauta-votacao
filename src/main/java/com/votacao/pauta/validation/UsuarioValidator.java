package com.votacao.pauta.validation;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioValidator {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarNome(String nome) {
        if (nome == null) {
            throw new BadRequestException("Nome de usuário inválido. Por favor, digite um nome válido.");
        }
    }

    public void validarNomeExistente(String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
        if (usuario.isPresent()) {
            throw new BadRequestException("Nome de usuário já existe. Por favor, escolha outro nome.");
        }
    }
}
