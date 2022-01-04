package com.backend.projects.unittesting.MockingDemo.Business;

import com.backend.projects.unittesting.MockingDemo.Service.SomeDataService;

public class SomeBusinessImp {

    private SomeDataService someDataService;

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    public int calculateSum(int[] data) {
        int sum = 0;

        for (int value : data) {  // for each int value in data
            sum += value;
        }

        return sum;
    }


    public int calculateSumUsingDataService() {
        int sum = 0;
        int[] data = someDataService.retrieveAllData();
        for(int value:data) {
            sum += value;
        }

        // someDataService.storeSum(sum) faz de conta que isto estava aqui
        return sum;
    }
}
