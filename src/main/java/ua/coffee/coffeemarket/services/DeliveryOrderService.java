package ua.coffee.coffeemarket.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.coffee.coffeemarket.models.DeliveryOrder;
import ua.coffee.coffeemarket.repositories.DeliveryOrderRepo;

import java.util.List;

@Data
@Service
public class DeliveryOrderService implements ServiceExample<DeliveryOrder> {

    private final DeliveryOrderRepo deliveryOrderRepo;

    @Transactional(readOnly = true)
    @Override
    public DeliveryOrder findById(Long id) {
        return deliveryOrderRepo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DeliveryOrder> findAll() {
        return deliveryOrderRepo.findAll();
    }

    @Transactional
    @Override
    public void save(DeliveryOrder deliveryOrder) {
        deliveryOrderRepo.save(deliveryOrder);
    }

    @Transactional
    @Override
    public void saveAll(List<DeliveryOrder> list) {
        deliveryOrderRepo.saveAll(list);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        deliveryOrderRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, DeliveryOrder deliveryOrder) {
        deliveryOrder.setId(id);
        deliveryOrderRepo.save(deliveryOrder);
    }

}
