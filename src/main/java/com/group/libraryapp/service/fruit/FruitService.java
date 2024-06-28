package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.repository.fruit.FruitRepository;
import com.group.libraryapp.service.fruit.request.FruitCreateServiceRequest;
import com.group.libraryapp.service.fruit.response.FruitStatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;

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
}
