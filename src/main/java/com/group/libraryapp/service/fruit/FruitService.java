package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.repository.fruit.FruitRepository;
import com.group.libraryapp.service.fruit.request.FruitCreateServiceRequest;
import com.group.libraryapp.service.fruit.response.FruitCountResponse;
import com.group.libraryapp.service.fruit.response.FruitResponse;
import com.group.libraryapp.service.fruit.response.FruitStatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FruitService {

    private final FruitRepository fruitRepository;

    @Transactional
    public void createFruit(FruitCreateServiceRequest request) {
        fruitRepository.save(request.toEntity());
    }

    public FruitStatResponse getFruitStat(String name) {
        List<Fruit> fruits = fruitRepository.findAllByName(name);
        if (fruits.isEmpty()) {
            return FruitStatResponse.builder()
                    .salesAmount(0)
                    .notSalesAmount(0)
                    .build();
        }

        return FruitStatResponse.builder()
                .salesAmount(getAmountSum(fruits, Fruit::isSold))
                .notSalesAmount(getAmountSum(fruits, Fruit::isNotSold))
                .build();
    }

    private long getAmountSum(List<Fruit> fruits, Predicate<Fruit> fruitFilter) {
        return fruits.stream()
                .filter(fruitFilter)
                .mapToLong(Fruit::getPrice)
                .sum();
    }

    @Transactional
    public void orderFruit(long id) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        fruit.order();
    }

    public FruitCountResponse getFruitCount(String name) {
        long count = fruitRepository.countByName(name);
        return FruitCountResponse.builder().count(count).build();
    }

    public List<FruitResponse> getFruitList(String option, long price) {
        List<Fruit> fruits = new ArrayList<>();
        if ("GTE".equals(option)) {
            fruits = fruitRepository.findAllByPriceGreaterThanEqualAndSoldYn(price, "N");
        } else if ("LTE".equals(option)) {
            fruits = fruitRepository.findAllByPriceLessThanEqualAndSoldYn(price, "N");
        }

        return fruits.stream()
                .map(FruitResponse::of)
                .collect(Collectors.toList());
    }
}
