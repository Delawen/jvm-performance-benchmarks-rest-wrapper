package jvm.performance.benchmarks.rest;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
public class DeadLoopEliminationBenchmarkIT {

    @RepeatedTest(value = 1000)
    public void testHelloEndpoint() {
        given()
                .when().get("/dead-loop/conditional-dead-loop")
                .then()
                .statusCode(200);
    }
}
