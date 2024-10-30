package ua.coffee.coffeemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.coffee.coffeemarket.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}