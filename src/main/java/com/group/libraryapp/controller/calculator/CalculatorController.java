package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController // API를 개발하려고 하는 클래스에는 @RestController 를 붙여주야 한다. ( @RestController 의 역할은 해당 클래스를 API의 진입 지점(API의 입구)으로 만들어준다. )
public class CalculatorController {

    @GetMapping("/v1/add") // (HTTP Method 가 GET 이고, HTTP Path 가 /add 인) API 를 정의한다.
    public int addTwoNumbers(
            @RequestParam int number1, // 쿼리(key와 value)를 통해서 데이터를 받는다. ( 넘어온 데이터를 함수 파라미터에 연결해줄 때는 @RequestParam 을 붙여주어야 한다. 그러면 같은 이름을 가진 쿼리의 값이 매핑되어 들어온다. )
            @RequestParam int number2
    ) {
        return number1 + number2;
    }

    @GetMapping("/v2/add")
    public int addTwoNumbers(
            CalculatorAddRequest request // 참고) 쿼리 파라미터가 많은 경우를 대비해서, 객체로 받도록 할 수도 있다.
    ) {
        return request.getNumber1() + request.getNumber2();
    }

    // =======================================================================

    @PostMapping("/v1/multiply") // (HTTP Method 가 POST 이고, HTTP Path 가 /multiply 인) API 를 정의한다.
    public int multiplyTwoNumbers(
            @RequestBody CalculatorMultiplyRequest request // HTTP Body 를 통해서 데이터를 받는다. ( @RequestBody : HTTP Body 안에 있는 JSON 을 파싱해서 객체로 변환시켜 주기 위한 작업을 처리해준다. )
    ) {
        return request.getNumber1() * request.getNumber2();
    }

}

// [ 덧셈 API Spec ]
// - HTTP Method: GET
// - HTTP Path: /v*/add
// - 쿼리(Key와 value): int number1 / int number2
// - API의 반환 결과: 숫자 (두 숫자의 덧셈 결과)

// [ 곱셈 API Spec ]
// - HTTP Method: POST
// - HTTP Path: /v*/multiply
// - HTTP Body (JSON): { "number1": 숫자, "number2": 숫자 }
// - API의 반환 결과: 숫자 (두 숫자의 곱셈 결과)

// 참고)
// - 하나의 Controller 클래스에 여러 API를 작성할 수 있다.