package com.javamastery.ch01_fundamentals.test_env;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloJavaTest {

    @Test
    void additionShouldReturnCorrectResult() {
        assertEquals(4, 2 + 2);
    }
}