package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.controller.fruit.request.FruitCreateRequest;
import com.group.libraryapp.controller.fruit.request.FruitOrderRequest;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.fruit.response.FruitStatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
