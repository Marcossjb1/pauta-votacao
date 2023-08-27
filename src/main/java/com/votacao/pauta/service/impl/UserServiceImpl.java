package com.votacao.pauta.service.impl;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.model.User;
import com.votacao.pauta.repository.UserRepository;
import com.votacao.pauta.service.UserService;
import com.votacao.pauta.validation.UserValidator;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;

    @Override
    public User createUser(User user) {
        if (!user.getName().isEmpty()) {
            userValidator.validateName(user.getName());
            return userRepository.save(user);
        }
        throw new BadRequestException("O nome não pode ser vazio.");
    }

    @Override
    public User searchUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new ObjectNotFoundException(id, User.class.getSimpleName());
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id, User.class.getSimpleName());
        }
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User newUser) {
        Optional<User> updateUserWithId = userRepository.findById(id);
        if (updateUserWithId.isPresent()) {
            User user = updateUserWithId.get();
            user.setName(newUser.getName());
            return userRepository.save(user);
        } else {
            throw new ObjectNotFoundException(id, User.class.getSimpleName());
        }
    }
}

