package com.group.libraryapp.service.fruit.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FruitStatResponse {
    private long salesAmount;
    private long notSalesAmount;

    @Builder
    private FruitStatResponse(long salesAmount, long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }
}
