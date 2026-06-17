package jvm.performance.benchmarks.rest;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
public class NQueensBenchmarkIT {

    @RepeatedTest(value = 1000)
    public void testHelloEndpoint() {
        given()
                .when().get("/nqueens")
                .then()
                .statusCode(200);

        given()
                .when().get("/nqueens/8")
                .then()
                .statusCode(200);

        given()
                .when().get("/nqueens/16")
                .then()
                .statusCode(200);
    }
}
