package com.github.dballinger.activityweather;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SemicircleTest {


    @Test
    public void shouldConvertDecimalDegreeToSemicircle() throws Exception {
        double degrees = 51.238820096477866;
        long semicircles = 611302935;
        Semicircle semicircle = new Semicircle();
        assertThat(semicircle.fromDegrees(degrees), is(semicircles));
    }

    @Test
    public void shouldConvertSemicircleToDecimalDegree() throws Exception {
        double degrees = 51.238820096477866;
        long semicircles = 611302935;
        Semicircle semicircle = new Semicircle();
        assertThat(semicircle.toDegrees(semicircles), is(degrees));
    }

}
