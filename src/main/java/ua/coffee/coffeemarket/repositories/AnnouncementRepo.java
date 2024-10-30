package ua.coffee.coffeemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.coffee.coffeemarket.models.Announcement;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {
}
