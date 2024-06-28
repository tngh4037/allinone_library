package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.controller.fruit.request.FruitCreateRequest;
import com.group.libraryapp.controller.fruit.request.FruitOrderRequest;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.fruit.response.FruitCountResponse;
import com.group.libraryapp.service.fruit.response.FruitResponse;
import com.group.libraryapp.service.fruit.response.FruitStatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FruitController {

    private final FruitService fruitService;

    @PostMapping("/api/v1/fruit")
    public void createFruit(@RequestBody FruitCreateRequest request) {
        fruitService.createFruit(request.toServiceRequest());
    }

    @PutMapping("/api/v1/fruit")
    public void orderFruit(@RequestBody FruitOrderRequest request) {
        fruitService.orderFruit(request.getId());
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse getFruitStat(@RequestParam String name) {
        return fruitService.getFruitStat(name);
    }

    @GetMapping("/api/v1/fruit/count")
    public FruitCountResponse getFruitCount(@RequestParam String name) {
        return fruitService.getFruitCount(name);
    }

    @GetMapping("/api/v1/fruit/list")
    public List<FruitResponse> getFruitList(@RequestParam String option,
                                            @RequestParam long price) {
        return fruitService.getFruitList(option, price);
    }

}
