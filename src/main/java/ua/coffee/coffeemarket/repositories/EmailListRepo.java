package ua.coffee.coffeemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.coffee.coffeemarket.models.EmailList;

public interface EmailListRepo extends JpaRepository<EmailList, Long> {
}
