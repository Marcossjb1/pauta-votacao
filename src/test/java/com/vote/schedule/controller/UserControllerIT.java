package com.vote.schedule.controller;

import com.vote.schedule.config.IntegrationTest;
import org.junit.Test;

import static com.vote.schedule.fixture.UserFixture.getUser;
import static com.vote.schedule.fixture.UserFixture.postUser;
import static com.vote.schedule.utils.BodyBuilder.with;

public class UserControllerIT extends IntegrationTest {

    @Test
    public void shouldSearchUser() {
        getUser()
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldCreateUser() {
        var userBody = with("id", 1L)
                .and("name", "marcos");

        postUser(userBody)
                .then()
                .statusCode(200);
    }
}

//TODO: CRIAR UMA CLASSE DE RAMDOM PARA GERAR DADOS ALEATÓRIOS PROS NOSSOS TESTES.