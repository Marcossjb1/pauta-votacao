package com.vote.schedule.service.impl;

import com.vote.schedule.exception.BadRequestException;
import com.vote.schedule.model.User;
import com.vote.schedule.repository.UserRepository;
import com.vote.schedule.validation.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock //MOCKAR UMA CLASSE É UTILIZAR DADOS POR DEFAULT SEM UTILIZAR OS DADOS REAIS
    private UserRepository userRepository;
    @Mock
    private UserValidator userValidator;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldCreateUser() {
        //TESTE UNITÁRIO DE DADOS INSERIDOS NA FUNÇÃO CRIAR USUARIO
        var user = new User();
        user.setId(1L);
        user.setName("Marcos");
        given(userRepository.save(user)).willReturn(user);
        var result = userService.createUser(user);
        then(result.getId()).isEqualTo(1L);
        then(result.getName()).isEqualTo("Marcos");
    }
    @Test
    public void shouldReturnErrorWhenUserNameInvalid() {
        //TESTE UNITÁRIO DE DADOS NÃO INSERIDOS PARA O NOME DE USUÁRIO
        var user = new User();
        user.setId(1L);
        user.setName("");
        thenThrownBy(() -> userService.createUser(user)).isInstanceOf(BadRequestException.class);
    }

    @Test
    public void shouldReturnUser() {
        var user = new User();
        user.setId(1L);
        user.setName("Gui");
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        var result = userService.searchUser(user.getId());
        then(result.getId()).isEqualTo(1L);
        then(result.getName()).isEqualTo("Gui");
    }
}
//TODO: CRIAR TESTE PARA VALIDAR O CENÁRIO DE ERRO AO TENTAR RETORNAR UM USUÁRIO.