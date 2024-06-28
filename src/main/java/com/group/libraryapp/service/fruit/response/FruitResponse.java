
package com.group.libraryapp.service.fruit.response;

import com.group.libraryapp.domain.fruit.Fruit;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FruitResponse {
    private String name;
    private long price;
    private LocalDate warehousingDate;

    @Builder
    private FruitResponse(String name, long price, LocalDate warehousingDate) {
        this.name = name;
        this.price = price;
        this.warehousingDate = warehousingDate;
    }

    public static FruitResponse of(Fruit fruit) {
        return FruitResponse.builder()
                .name(fruit.getName())
                .price(fruit.getPrice())
                .warehousingDate(fruit.getWarehousingDate())
                .build();
    }
}
