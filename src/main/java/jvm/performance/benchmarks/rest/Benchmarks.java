package jvm.performance.benchmarks.rest;

import com.ionutbalosin.jvm.performance.benchmarks.miscellaneous.fibonacci.recursive.RecursiveFormula;
import com.ionutbalosin.jvm.performance.benchmarks.miscellaneous.nqueens.backtracking.NQueensBacktracking;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.*;

@Path("/")
public class Benchmarks {

    private Random r = new Random();

    @GET
    @Path("/fibonacci/{number:\\d+}")
    public BigInteger fibonacci(@DefaultValue("-1") Long number) {
        if (number < 0) {
            number = r.nextLong(100);
        }
        return RecursiveFormula.fibonacci(number, BigInteger.ZERO, BigInteger.ONE);
    }

    @GET
    @Path("/fibonacci")
    public BigInteger fibonacci() {
        return fibonacci(r.nextLong(100));
    }

    @GET
    @Path("/nqueens/{number:\\d+}")
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
    @Path("/nqueens")
    public byte[][] nqueens() throws ExecutionException, InterruptedException {
        return nqueens(r.nextBoolean() ? 16 : 8);
    }

}
