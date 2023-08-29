package com.vote.schedule.service;

import com.vote.schedule.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User searchUser(Long id);
    void deleteUser(Long id);
    List<User> listAllUsers();
    User updateUser(Long id, User newUser);
}
