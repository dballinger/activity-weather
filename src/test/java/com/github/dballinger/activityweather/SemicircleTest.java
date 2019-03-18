package com.github.dballinger.activityweather;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SemicircleTest {

    @Test
    public void shouldConvertSemicircleToDecimalDegree() throws Exception {
        double degrees = 51.238820096477866;
        int semicircles = 611302935;
        Semicircle semicircle = new Semicircle(semicircles);
        assertThat(semicircle.toDegrees(), is(new DecimalDegree(degrees)));
    }

}
