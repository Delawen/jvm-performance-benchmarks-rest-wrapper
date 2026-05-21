package jvm.performance.benchmarks.rest;

import com.ionutbalosin.jvm.performance.benchmarks.miscellaneous.fibonacci.recursive.RecursiveFormula;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.math.BigInteger;
import java.util.Random;

@Path("/fibonacci/")
public class FibonacciBenchmark {
    private Random r = new Random(2398L);

    @GET
    @Path("/{number:\\d+}")
    public BigInteger fibonacci(@DefaultValue("-1") Long number) {
        if (number < 0) {
            number = r.nextLong(100);
        }
        return RecursiveFormula.fibonacci(number, BigInteger.ZERO, BigInteger.ONE);
    }

    @GET
    @Path("/")
    public BigInteger fibonacci() {
        return fibonacci(r.nextLong(100));
    }

}
