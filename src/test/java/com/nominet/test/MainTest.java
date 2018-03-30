package com.nominet.test;

import org.junit.Test;

public class MainTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
        final String[] argumentArray = {"exampleArgument"};
        Main.main(argumentArray);
    }
}
