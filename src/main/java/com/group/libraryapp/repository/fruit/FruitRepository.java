package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    List<Fruit> findAllByName(String name);
}
