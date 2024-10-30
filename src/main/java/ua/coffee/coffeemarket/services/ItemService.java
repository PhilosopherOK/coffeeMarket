package ua.coffee.coffeemarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.coffee.coffeemarket.models.Item;
import ua.coffee.coffeemarket.repositories.ItemRepository;

import java.util.List;


@AllArgsConstructor
@Service
public class ItemService implements ServiceExample<Item> {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Boolean findIfExist(){
        List<Item> all = itemRepository.findAll();
        if(!(all.size() == 0)){
            return false;
        }
        return true;
    }

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    @Override
    public void saveAll(List<Item> list) {
        itemRepository.saveAll(list);
    }

    public void checkToItemValid(Item item) throws Exception {
        if (item.getName().isBlank() || item.getLocalName().isBlank() || Double.isNaN(item.getPrice()) || item.getAmount() <= 0) {
            throw new IllegalArgumentException("Fields must not be empty and numbers must be valid");
        }
        if (item.getName().length() > 30 || item.getLocalName().length() > 30 || item.getPrice() > 99999 || item.getAmount() > 99999) {
            throw new IllegalArgumentException("Fields must not exceed the limits: strings of 30 characters, numbers 99999");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, Item item) {
        item.setId(id);
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}