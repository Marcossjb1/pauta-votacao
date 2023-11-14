package com.vote.schedule.controller;

import static io.restassured.RestAssured.given;

import com.vote.schedule.config.IntegrationTest;
import org.junit.Test;

public class ScheduleControllerIT extends IntegrationTest {

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
