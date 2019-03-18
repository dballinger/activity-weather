package com.github.dballinger.activityweather;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DecimalDegreeTest {

    @Test
    public void shouldConvertDecimalDegreeToSemicircle() throws Exception {
        double degrees = 51.238820096477866;
        long semicircles = 611302935;
        DecimalDegree decimalDegree = new DecimalDegree(degrees);
        assertThat(decimalDegree.toSemicircle(), is(new Semicircle(semicircles)));
    }

}
