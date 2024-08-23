package com.prw3.dao;

import java.util.List;

public interface DAO<T> {
    T findByName(String name);
    List<T> findAll();
    void save(T entity);
    void delete(T entity);
}
