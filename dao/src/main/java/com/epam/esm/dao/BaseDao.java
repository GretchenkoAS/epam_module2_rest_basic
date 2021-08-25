package com.epam.esm.dao;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface responsible for processing CRUD operations for entities
 *
 * @author Andrey Gretchenko
 */
public interface BaseDao<T> {
    /**
     * Returns list of objects of all entities from repository.
     *
     * @return List of entities in repository
     */
    List<T> findAll();

    /**
     * Returns Optional of object for entity with provided id from repository.
     *
     * @param id id of entity to find
     * @return Optional<T> of entity with provided id in repository
     */
    Optional<T> findOne(Long id);

    /**
     * Returns Optional of object for entity with provided name from repository.
     *
     * @param name name of entity to find
     * @return Optional<T> of entity with provided name in repository
     */
    Optional<T> findByName(String name);

    /**
     * Adds entity object to repository.
     *
     * @param obj of entity to add to repository
     * @return object of entity in repository
     */
    T add(T obj);

    /**
     * Updates object for entity with provided id in repository.
     *
     * @param obj of entity to update
     * @param id  of entity to update
     * @return object of updated entity in repository
     */
    T update(T obj, Long id);

    /**
     * Removes entity with provided id from repository.
     *
     * @param id id of entity to remove
     */
    void delete(Long id);
}
