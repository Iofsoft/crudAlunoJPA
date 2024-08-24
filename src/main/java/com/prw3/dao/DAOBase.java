package com.prw3.dao;

import java.util.Collection;

public interface DAOBase<T> {
    Collection<T> findById(Long id);
    Collection<T> findByName(String name);
    Collection<T> findAll();
    void save(T entity);
    void delete(T entity);
}
