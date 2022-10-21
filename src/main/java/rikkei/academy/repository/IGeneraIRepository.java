package rikkei.academy.repository;

import rikkei.academy.model.Customer;

import java.util.List;

public interface IGeneraIRepository<T> {
    List<Customer>findAll();
    T findByID(Long id);
    void save(T t);
    void remove(Long id);
}
