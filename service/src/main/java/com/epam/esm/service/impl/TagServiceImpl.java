package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public TagDto find(Long id) {
        Optional<Tag> tagOptional = tagDao.findOne(id);
        if (!tagOptional.isPresent()) {
            //fixme add throw
        }
        return mapper.mapEntityToDto(tagOptional.get());
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = tagDao.findAll();
        return tags.stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void add(TagDto tagDTO) {
        Tag tag = mapper.mapDtoToEntity(tagDTO);
        if (tagDao.findByName(tag.getName()).isPresent()) {
            //fixme add throw
        }
        tagDao.add(tag);
    }

    @Override
    public void delete(Long id) {
        tagDao.delete(id);
        //fixme add throw
    }

    @Override
    public TagDto findByName(String name) {
        Optional<Tag> tagOptional = tagDao.findByName(name);
        if (!tagOptional.isPresent()) {
            //fixme add throw
        }
        return tagOptional.map(mapper::mapEntityToDto).get();
    }
}
