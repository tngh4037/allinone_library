package com.group.libraryapp.dto.exam.response;

import com.group.libraryapp.dto.exam.request.CalculatorRequest;
import lombok.Getter;

@Getter
public class CalculatorResponse {

    private int add;
    private int minus;
    private int multiply;

    public CalculatorResponse(CalculatorRequest request) {
        this.add = request.getNum1() + request.getNum2();
        this.minus = request.getNum1() - request.getNum2();
        this.multiply = request.getNum1() * request.getNum2();
    }
}
