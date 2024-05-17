package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class GreetingResourceServiceTest {

    @Test
    fun `test GET returns json content correctly`() {
        given()
          .`when`().get("/hello")
          .then()
             .statusCode(200)
             .header("Content-Type", CoreMatchers.containsString("application/json"))
            .body(`is`("{\"foo\":\"bar\"}"))
    }

    @Test
    fun `test POST returns json content correctly`() {
        given()
            .`when`().post("/hello")
            .then()
            .statusCode(200)
            .header("Content-Type", CoreMatchers.containsString("application/json"))
            .body(`is`("{\"foo\":\"bar\"}"))
    }

}