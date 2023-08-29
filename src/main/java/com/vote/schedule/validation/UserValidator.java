package com.vote.schedule.validation;

import com.vote.schedule.exception.BadRequestException;
import com.vote.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validateName(String name) {
        if (userRepository.findByName(name).isPresent()) {
            throw new BadRequestException("Nome de usuário já existe. Por favor, escolha outro nome.");
        }
        if (name == null) {
            throw new BadRequestException("Nome de usuário inválido. Por favor, digite um nome válido.");
        }
    }
}
