package com.vote.schedule.controller;

import com.vote.schedule.config.IntegrationTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserControllerIT extends IntegrationTest {

    @Test
    public void getUser() {
        given()
                .basePath("/user")
                .when()
                .get("/list-users")
                .then()
                .statusCode(200);
    }
}
