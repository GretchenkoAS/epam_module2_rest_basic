package com.epam.esm.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    List<T> findAll();

    Optional<T> findOne(Long id);

    Optional<T> findByName(String name);

    T add(T obj);

    T update(T obj, Long id);

    void delete(Long id);
}
