package com.group.libraryapp.dto.exam.request;

import lombok.Getter;

@Getter
public class CalculatorRequest {

    private final int num1;
    private final int num2;

    public CalculatorRequest(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
}
