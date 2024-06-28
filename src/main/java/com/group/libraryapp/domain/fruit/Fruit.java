package com.group.libraryapp.domain.fruit;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Fruit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;
    private LocalDate warehousingDate;
    private long price;
    private String soldYn;

    @Builder
    private Fruit(String name, LocalDate warehousingDate, long price, String soldYn) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
        this.soldYn = soldYn;
    }

    public boolean isSold() {
        return "Y".equals(this.soldYn);
    }

    public boolean isNotSold() {
        return "N".equals(this.soldYn);
    }

    public void order() {
        this.soldYn = "Y";
    }
}
