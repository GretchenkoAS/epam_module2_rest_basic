package com.epam.esm.service;

import com.epam.esm.dto.TagDTO;

import java.util.List;

public interface TagService {
    TagDTO add(TagDTO tagDTO);

    TagDTO find(Long id);

    List<TagDTO> findAll();

    TagDTO findByName(String name);

    boolean exist(TagDTO tagDTO);

    void delete(Long id);
}
