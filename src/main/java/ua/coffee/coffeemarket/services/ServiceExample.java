package ua.coffee.coffeemarket.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceExample<T> {
    @Transactional(readOnly = true)
    public T findById(Long id);

    @Transactional(readOnly = true)
    public List<T> findAll();

    @Transactional
    public void save(T t);

    @Transactional
    public void saveAll(List<T> list);

    @Transactional
    public void deleteById(Long id);

    @Transactional
    public void update(Long id, T t);
}
