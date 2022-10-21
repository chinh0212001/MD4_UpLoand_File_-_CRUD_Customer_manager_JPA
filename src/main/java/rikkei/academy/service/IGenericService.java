package rikkei.academy.service;

import java.util.List;

public interface IGenericService <T>{
    List<T>findAll();
    T findById(long id);
    void save(T t);
    void  remove(Long id);
}
