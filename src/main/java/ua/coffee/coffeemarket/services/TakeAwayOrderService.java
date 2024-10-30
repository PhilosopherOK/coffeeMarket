package ua.coffee.coffeemarket.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.coffee.coffeemarket.models.TakeAwayOrder;
import ua.coffee.coffeemarket.repositories.TakeAwayOrderRepo;
import ua.coffee.coffeemarket.utils.PhoneValidator;

import java.time.LocalDate;
import java.util.List;

@Service
@Data
public class TakeAwayOrderService implements ServiceExample<TakeAwayOrder> {
    private final TakeAwayOrderRepo takeAwayOrderRepo;


    @Transactional(readOnly = true)
    @Override
    public TakeAwayOrder findById(Long id) {
        return takeAwayOrderRepo.findById(id).orElseThrow(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TakeAwayOrder> findAll() {
        return takeAwayOrderRepo.findAll();
    }

    @Transactional
    @Override
    public void save(TakeAwayOrder takeAwayOrder) {
        takeAwayOrderRepo.save(takeAwayOrder);
    }

    @Transactional
    @Override
    public void saveAll(List<TakeAwayOrder> list) {
        takeAwayOrderRepo.saveAll(list);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        takeAwayOrderRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, TakeAwayOrder takeAwayOrder) {
        takeAwayOrder.setId(id);
        takeAwayOrderRepo.save(takeAwayOrder);
    }
}
