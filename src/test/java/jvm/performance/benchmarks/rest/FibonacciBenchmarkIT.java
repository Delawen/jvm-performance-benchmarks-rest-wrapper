package jvm.performance.benchmarks.rest;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
public class FibonacciBenchmarkIT {

    @RepeatedTest(value = 1000)
    public void testHelloEndpoint() {
        given()
                .when().get("/fibonacci")
                .then()
                .statusCode(200);

        given()
                .when().get("/fibonacci/100")
                .then()
                .statusCode(200);
    }
}
