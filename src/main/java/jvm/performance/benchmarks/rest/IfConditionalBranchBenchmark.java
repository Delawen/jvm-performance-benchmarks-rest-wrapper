package jvm.performance.benchmarks.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Random;

//Based on com.ionutbalosin.jvm.performance.benchmarks.compiler.IfConditionalBranchBenchmark
@Path("/conditional")
public class IfConditionalBranchBenchmark {
    private final Random random = new Random(16384L);
    private int size = 16384;
    private int[] array;

    public IfConditionalBranchBenchmark() {
        array = new int[this.size];
        for(int i = 0; i < this.size; ++i) {
            this.array[i] = this.random.nextInt(4096);
        }
    }

    @GET
    @Path("/no-if")
    public int no_if_branch() {
        int sum = 0;

        for(int value : this.array) {
            sum += value;
        }

        return sum;
    }

    @GET
    @Path("/predictable-if")
    public int predictable_if_branch() {
        int sum = 0;

        for(int value : this.array) {
            if (value < 4096) {
                sum += value;
            }
        }

        return sum;
    }

    @GET
    @Path("/unpredictable-if")
    public int unpredictable_if_branch() {
        int sum = 0;

        for(int value : this.array) {
            if (value <= 2048) {
                sum += value;
            }
        }

        return sum;
    }

}
