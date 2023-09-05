package com.vote.schedule.controller;

import com.vote.schedule.model.User;
import com.vote.schedule.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public User buscarUsuario(@PathVariable Long id) {
        return userServiceImpl.searchUser(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
    }

    @GetMapping("/list-users")
    public List<User> listAllUsers() {
        return userServiceImpl.listAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userServiceImpl.updateUser(id, user);
    }
}
