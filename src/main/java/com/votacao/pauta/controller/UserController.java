package com.votacao.pauta.controller;

import com.votacao.pauta.model.User;
import com.votacao.pauta.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public User buscarUsuario(@PathVariable Long id) {
        return userServiceImpl.searchUser(id);
    }

    @PostMapping("/inserir")
    public User inserirUsuario(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
    }

    @GetMapping("/listar-usuarios")
    public List<User> listarTodosUsuarios() {
        return userServiceImpl.listAllUsers();
    }

    @PutMapping("/{id}")
    public User atualizarUsuario(@PathVariable Long id, @RequestBody User user) {
        return userServiceImpl.updateUser(id, user);
    }
}
