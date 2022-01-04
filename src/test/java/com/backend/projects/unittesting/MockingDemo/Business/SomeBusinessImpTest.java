package com.backend.projects.unittesting.MockingDemo.Business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeBusinessImpTest {

    @Test
    void calculateSum_basic() {
        SomeBusinessImp businessImp = new SomeBusinessImp();

        int actualResult = businessImp.calculateSum(new int[]{1, 2, 3});

        int expectedResult = 6;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateSum_empty() {
        SomeBusinessImp businessImp = new SomeBusinessImp();

        int actualResult = businessImp.calculateSum(new int[]{});

        int expectedResult = 0;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateSum_one() {
        SomeBusinessImp businessImp = new SomeBusinessImp();

        int actualResult = businessImp.calculateSum(new int[]{5});

        int expectedResult = 5;

        assertEquals(expectedResult, actualResult);
    }

}