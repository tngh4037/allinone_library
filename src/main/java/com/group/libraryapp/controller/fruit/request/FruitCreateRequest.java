package com.group.libraryapp.controller.fruit.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group.libraryapp.service.fruit.request.FruitCreateServiceRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class FruitCreateRequest {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate warehousingDate;

    private long price;

    public FruitCreateServiceRequest toServiceRequest() {
        return FruitCreateServiceRequest.builder()
                .name(name)
                .warehousingDate(warehousingDate)
                .price(price)
                .build();
    }
}
