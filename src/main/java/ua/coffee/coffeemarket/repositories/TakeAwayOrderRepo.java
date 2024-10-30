package ua.coffee.coffeemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.coffee.coffeemarket.models.TakeAwayOrder;

public interface TakeAwayOrderRepo extends JpaRepository<TakeAwayOrder, Long> {
}
