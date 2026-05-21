package jvm.performance.benchmarks.rest;

import com.ionutbalosin.jvm.performance.benchmarks.miscellaneous.nqueens.backtracking.NQueensBacktracking;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Random;
import java.util.concurrent.*;

@Path("/nqueens")
public class NQueensBenchmark {
    private final Random r = new Random(1029873L);
    @GET
    @Path("/{number:\\d+}")
    public byte[][] nqueens(@DefaultValue("-1") Integer number) throws ExecutionException, InterruptedException {
        var startingRow = r.nextInt(number);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<byte[][]> future = executor.submit(() -> NQueensBacktracking.placeNQueens(number, startingRow));

        try {
            return future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw new InterruptedException(e.getMessage());
        } finally {
            executor.shutdown();
        }

    }

    @GET
    @Path("/")
    public byte[][] nqueens() throws ExecutionException, InterruptedException {
        return nqueens(r.nextBoolean() ? 16 : 8);
    }

}
