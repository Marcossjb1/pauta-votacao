package com.vote.schedule.fixture;

import com.vote.schedule.utils.BodyBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserFixture {

  public static Response getUser() {
    return given()
        .basePath("/user")
        .when()
        .get("/list-users");
  }

  public static Response postUser(BodyBuilder body) {
    return given()
        .basePath("/user")
        .contentType(ContentType.JSON)
        .body(body.build())
        .when()
        .post("/create");
  }
}
