package ua.coffee.coffeemarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.coffee.coffeemarket.models.Announcement;
import ua.coffee.coffeemarket.repositories.AnnouncementRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementService implements ServiceExample<Announcement>{
    private final AnnouncementRepo announcementRepo;

    @Transactional
    public void save(Announcement announcement){
        if (announcement.getDescription().isBlank() || announcement.getTopic().isBlank()) {
            throw new IllegalArgumentException("Fields must not be empty");
        }
        if (announcement.getDescription().length() > 1000 || announcement.getTopic().length() > 30) {
            throw new IllegalArgumentException("Fields must not exceed the limits: Topic 30, Description of 1000 characters");
        }
      announcementRepo.save(announcement);
    }

    @Transactional
    @Override
    public void saveAll(List<Announcement> list) {
        announcementRepo.saveAll(list);
    }


    @Transactional(readOnly = true)
    @Override
    public Announcement findById(Long id) {
        return announcementRepo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Announcement> findAll(){
        return announcementRepo.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        announcementRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, Announcement announcement) {
        announcement.setId(id);
        announcementRepo.save(announcement);
    }
}
