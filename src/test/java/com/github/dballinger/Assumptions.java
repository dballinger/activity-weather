package com.github.dballinger;

import org.junit.Test;

import java.util.List;
import java.util.function.BinaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Assumptions {
    @Test
    public void shouldCalculateMeanForNumbers() throws Exception {
        List<Double> xs = List.of(1.0, 3.0);
        Double mean = xs.stream().reduce(new Acc()).get();
        assertThat(mean, is(2.0));
    }

    @Test
    public void shouldCalculateMeanForMoreThan2Numbers() throws Exception {
        List<Double> xs = List.of(1.0, 2.0, 3.0, 6.0);
        Double mean = xs.stream().reduce(new Acc()).get();
        assertThat(mean, is(3.0));
    }

    class Acc implements BinaryOperator<Double> {
        private int position = 1;

        @Override
        public Double apply(Double x1, Double x2) {
            return x1 + (x2 - x1) / ++position;
        }
    }
}
