package com.votacao.pauta.validation;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UsuarioValidator {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private void validarNome(String nome) {
        if (nome == null) {
            throw new BadRequestException("Nome de usuário inválido. Por favor, digite um nome válido.");
        }
    }

    private void validarNomeExistente(String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
        if (usuario.isPresent()) {
            throw new BadRequestException("Nome de usuário já existe. Por favor, escolha outro nome.");
        }
    }
}
//TODO: CHAMAR ESSAS DUAS FUNÇÕES NO MEU SERVIÇO DO USUÁRIO