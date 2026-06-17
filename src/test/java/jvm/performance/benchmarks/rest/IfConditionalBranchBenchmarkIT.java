package jvm.performance.benchmarks.rest;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
public class IfConditionalBranchBenchmarkIT {

    @RepeatedTest(value = 1000)
    public void testHelloEndpoint() {
        given()
                .when().get("/conditional/unpredictable-if")
                .then()
                .statusCode(200);
    }
}
