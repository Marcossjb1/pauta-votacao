package com.votacao.pauta.service;

import com.votacao.pauta.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User searchUser(Long id);
    void deleteUser(Long id);
    List<User> listAllUsers();
    User updateUser(Long id, User novoUser);
}
