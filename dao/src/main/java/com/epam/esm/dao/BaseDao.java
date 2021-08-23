package com.epam.esm.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    List<T> findAll();

    Optional<T> findOne(Long id);

    Optional<T> findByName(String name);

    boolean add(T obj);

    boolean update(T obj, Long id);

    boolean delete(Long id);
}
