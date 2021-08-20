package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    private final TagMapper mapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagMapper mapper) {
        this.tagDao = tagDao;
        this.mapper = mapper;
    }

    @Override
    public TagDto add(TagDto tagDTO) {
        return null;
    }

    @Override
    public TagDto find(Long id) {
        return null;
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = tagDao.findAll();
        return tags.stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public TagDto findByName(String name) {
        return null;
    }

    @Override
    public boolean exist(TagDto tagDTO) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
