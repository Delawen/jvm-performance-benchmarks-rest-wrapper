package jvm.performance.benchmarks.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Random;

//Based on com.ionutbalosin.jvm.performance.benchmarks.compiler.DeadLoopEliminationBenchmark
@Path("/dead-loop")
public class DeadLoopEliminationBenchmark {

    private final Random random = new Random(16384);
    private final int DEAD_LOOP_LIMIT = 20_000;
    private int defaultValue = random.nextInt(64);
    private boolean useLoopResult = false;

    @GET
    @Path("/conditional-dead-loop")
    public int conditional_dead_loop() {
        int result = defaultValue << 1;

        int i = 0, dummy = 0;
        for (; i < DEAD_LOOP_LIMIT; i++) {
            dummy = Math.max(dummy + i, i);
        }

        // because "useLoopResult" is always false, this branch is never taken
        if (useLoopResult) {
            result += dummy;
        }

        return result;
    }
}
