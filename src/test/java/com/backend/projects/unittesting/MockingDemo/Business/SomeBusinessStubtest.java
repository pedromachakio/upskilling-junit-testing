package com.backend.projects.unittesting.MockingDemo.Business;

import com.backend.projects.unittesting.MockingDemo.Service.SomeDataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeDataServiceStub implements SomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[]{1, 2, 3};
    }
}

class SomeDataServiceStubEmpty implements SomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[]{};
    }
}

class SomeDataServiceStubOne implements SomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[]{5};
    }
}

class SomeBusinessStubtest {

    @Test
    void calculateSumUsingDataService_basic() {
        SomeBusinessImp businessImp = new SomeBusinessImp();
        businessImp.setSomeDataService(new SomeDataServiceStub());

        int actualResult = businessImp.calculateSumUsingDataService();

        int expectedResult = 6;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateSumUsingDataService_empty() {
        SomeBusinessImp businessImp = new SomeBusinessImp();

        businessImp.setSomeDataService(new SomeDataServiceStubEmpty());

        int actualResult = businessImp.calculateSumUsingDataService();

        int expectedResult = 0;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateSumUsingDataService_one() {
        SomeBusinessImp businessImp = new SomeBusinessImp();

        businessImp.setSomeDataService(new SomeDataServiceStubOne());

        int actualResult = businessImp.calculateSumUsingDataService();

        System.out.println(actualResult);

        int expectedResult = 5;

        assertEquals(expectedResult, actualResult);
    }

}