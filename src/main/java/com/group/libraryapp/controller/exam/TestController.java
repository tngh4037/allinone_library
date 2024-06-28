package com.group.libraryapp.controller.exam;

import com.group.libraryapp.dto.exam.request.AddRequest;
import com.group.libraryapp.dto.exam.response.DayOfTheWeekResponse;
import com.group.libraryapp.dto.exam.request.CalculatorRequest;
import com.group.libraryapp.dto.exam.response.CalculatorResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class TestController {

    @GetMapping("/api/v1/calc")
    public CalculatorResponse getCalculateResult(CalculatorRequest request) {
        return new CalculatorResponse(request);
    }

    @GetMapping("/api/v1/day-of-the-week")
    public DayOfTheWeekResponse getCalculateResult(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return new DayOfTheWeekResponse(date);
    }

    @PostMapping("/api/v1/add")
    public Integer add(@RequestBody AddRequest request) {
        return request.getNumber().stream().mapToInt(Integer::intValue).sum();
    }
}
