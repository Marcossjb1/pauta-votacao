package com.vote.schedule.service.impl;

import com.vote.schedule.exception.BadRequestException;
import com.vote.schedule.model.User;
import com.vote.schedule.repository.UserRepository;
import com.vote.schedule.validation.UserValidator;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserValidator userValidator;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldCreateUser() {
        var user = mock(User.class);

        given(user.getId()).willReturn(1L);
        given(user.getName()).willReturn("Marcos");
        given(userRepository.save(user)).willReturn(user);

        var result = userService.createUser(user);

        then(result.getId()).isEqualTo(1L);
        then(result.getName()).isEqualTo("Marcos");
    }

    @Test
    public void shouldReturnErrorWhenUserNameInvalid() {
        var user = mock(User.class);

        given(user.getName()).willReturn("");

        thenThrownBy(() -> userService.createUser(user))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    public void shouldReturnUser() {
        var user = mock(User.class);

        given(user.getId()).willReturn(1L);
        given(user.getName()).willReturn("Gui");
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        var result = userService.searchUser(user.getId());

        then(result.getId()).isEqualTo(1L);
        then(result.getName()).isEqualTo("Gui");
    }

    @Test
    public void shouldReturnErrorWhenUserNotExist(){
        thenThrownBy(() -> userService.searchUser(null))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldReturnErrorForUserDeleted(){
        thenThrownBy(() -> userService.deleteUser(null))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldDeletedUser(){
        var user = mock(User.class);

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    public void shouldListUsers(){
        var userOne = mock(User.class);
        var userTwo = mock(User.class);
        var listUsers = List.of(userOne,userTwo);

        given(userOne.getId()).willReturn(1L);
        given(userOne.getName()).willReturn("Mailão");

        given(userTwo.getId()).willReturn(2L);
        given(userTwo.getName()).willReturn("Gui");
        given(userRepository.findAll()).willReturn(listUsers);

        var result = userService.listAllUsers();

        then(result.get(0).getId()).isEqualTo(1L);
        then(result.get(0).getName()).isEqualTo("Mailão");

        then(result.get(1).getId()).isEqualTo(2L);
        then(result.get(1).getName()).isEqualTo("Gui");
    }
    @Test
    public void shouldReturnUpdateUser(){
        var user = mock(User.class);
        var newUser = mock(User.class);

        given(user.getName()).willReturn("Marcos");
        given(newUser.getId()).willReturn(1L);
        given(newUser.getName()).willReturn("Gui");
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(userService.updateUser(1L, newUser)).willReturn(newUser);

        var updateUser = userService.updateUser(1L, user);

        then(updateUser.getId()).isEqualTo(1L);
        then(updateUser.getName()).isEqualTo("Gui");
    }

    @Test
    public void shouldReturnErrorUpdateUser(){
        thenThrownBy(() -> userService.updateUser(null,null))
                .isInstanceOf(ObjectNotFoundException.class);
    }
}

