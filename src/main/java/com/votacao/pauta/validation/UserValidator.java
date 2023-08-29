package com.votacao.pauta.validation;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    @Autowired
    private UserRepository userRepository;

    public void validateName(String nome) {
        if (userRepository.findByNome(nome).isPresent()) {
            throw new BadRequestException("Nome de usuário já existe. Por favor, escolha outro nome.");
        }
        if (nome == null) {
            throw new BadRequestException("Nome de usuário inválido. Por favor, digite um nome válido.");
        }
    }
}
