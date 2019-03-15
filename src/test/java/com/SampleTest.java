package com;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SampleTest {
    @Test
    public void shouldDoSomething() throws Exception {
        assertThat(1, is(1));
    }
}
