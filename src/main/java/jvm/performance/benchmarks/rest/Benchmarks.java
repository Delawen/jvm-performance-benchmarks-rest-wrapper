package jvm.performance.benchmarks.rest;

import com.ionutbalosin.jvm.performance.benchmarks.miscellaneous.fibonacci.recursive.RecursiveFormula;
import com.ionutbalosin.jvm.performance.benchmarks.miscellaneous.nqueens.backtracking.NQueensBacktracking;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.math.BigInteger;
import java.util.Random;

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
    public byte[][] nqueens(@DefaultValue("-1") Integer number)  {
        var startingRow = r.nextInt(number);
        return NQueensBacktracking.placeNQueens(number, startingRow);
    }

    @GET
    @Path("/nqueens")
    public byte[][] nqueens() {
        return nqueens(r.nextInt(64));
    }

}
