package com.epam.esm.service;

import com.epam.esm.dto.TagDto;

import java.util.List;

public interface TagService {
    TagDto add(TagDto tagDTO);

    TagDto find(Long id);

    List<TagDto> findAll();

    TagDto findByName(String name);

    void delete(Long id);

    boolean exist(TagDto tagDto);
}
