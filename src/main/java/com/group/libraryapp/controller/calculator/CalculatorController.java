package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // API를 개발하려고 하는 클래스에는 @RestController 를 붙여주야 한다. ( @RestController 의 역할은 해당 클래스를 API의 진입 지점(API의 입구)으로 만들어준다. )
public class CalculatorController {

    @GetMapping("/v1/add") // (HTTP Method 가 GET 이고, HTTP Path 가 /add 인) API 르 정의한다.
    public int addTwoNumbers(
            @RequestParam int number1, // 쿼리(key와 value)를 통해서 넘어온 데이터를 함수 파라미터에 연결해줄 때는 @RequestParam 을 붙여주어야 한다. (그러면 같은 이름을 가진 쿼리의 값이 매핑되어 들어온다.)
            @RequestParam int number2
    ) {
        return number1 + number2;
    }

    @GetMapping("/v2/add")
    public int addTwoNumbers(
            CalculatorAddRequest request // 쿼리 파라미터가 많은 경우를 대비해서, 객체로 받도록 할 수도 있다.
    ) {
        return request.getNumber1() + request.getNumber2();
    }
}

// [ 덧셈 API Spec ]
// - HTTP Method: GET
// - HTTP Path: /v*/add
// - 쿼리(Key와 value): int number1 / int number2
// - API의 반환 결과: 숫자 (두 숫자의 덧셈 결과)

