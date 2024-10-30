package ua.coffee.coffeemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.coffee.coffeemarket.models.DeliveryOrder;

public interface DeliveryOrderRepo extends JpaRepository<DeliveryOrder, Long> {
}
