package com.epam.esm.mapper;

public interface Mapper<T, E> {
    E mapEntityToDto(T entity);
    T mapDtoToEntity(E dto);
}
