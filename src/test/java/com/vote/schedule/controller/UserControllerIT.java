package com.vote.schedule.controller;

import com.vote.schedule.config.IntegrationTest;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static com.vote.schedule.fixture.UserFixture.getUser;
import static com.vote.schedule.fixture.UserFixture.postUser;
import static com.vote.schedule.random.Random.firstName;
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
        var name = firstName();

        var userBody = with("id", 1L)
                .and("name", name);

        postUser(userBody)
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is(name));
    }
}

//TODO: CRIAR UMA CLASSE DE RAMDOM PARA GERAR DADOS ALEATÓRIOS PROS NOSSOS TESTES.