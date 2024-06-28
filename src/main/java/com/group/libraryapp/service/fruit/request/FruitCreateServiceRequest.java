package com.group.libraryapp.service.fruit.request;

import com.group.libraryapp.domain.fruit.Fruit;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FruitCreateServiceRequest {
    private String name;
    private LocalDate warehousingDate;
    private long price;

    @Builder
    private FruitCreateServiceRequest(String name, LocalDate warehousingDate, long price) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }

    public Fruit toEntity() {
        return Fruit.builder()
                .name(name)
                .warehousingDate(warehousingDate)
                .soldYn("N")
                .price(price)
                .build();
    }
}
