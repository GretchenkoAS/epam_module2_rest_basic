package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exeption.CustomException;
import com.epam.esm.exeption.ErrorCode;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final static String ID = "id";
    private final static String NAME = "name";


    private final TagDao tagDao;
    private final TagMapper mapper;
    private final TagValidator tagValidator;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagMapper mapper, TagValidator tagValidator) {
        this.tagDao = tagDao;
        this.mapper = mapper;
        this.tagValidator = tagValidator;
    }

    @Override
    public TagDto find(Long id) {
        Optional<Tag> tagOptional = tagDao.findOne(id);
        if (tagOptional.isEmpty()) {
            throw new CustomException(ErrorCode.TAG_NOT_FOUND, ID, id);
        }
        return mapper.mapEntityToDto(tagOptional.get());
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = tagDao.findAll();
        return tags.stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto add(TagDto tagDto) {
        if(!tagValidator.isValidName(tagDto.getName())){
            throw new CustomException(ErrorCode.TAG_FIELD_INVALID, NAME, tagDto.getName());
        }
        Tag tag = mapper.mapDtoToEntity(tagDto);
        if (tagDao.findByName(tag.getName()).isPresent()) {
            throw new CustomException(ErrorCode.TAG_ALREADY_EXIST, NAME, tagDto.getName());
        }
        Tag tagInDb = tagDao.add(tag);
        return mapper.mapEntityToDto(tagInDb);
    }

    @Override
    public boolean exist(TagDto tagDto) {
        Tag tag = mapper.mapDtoToEntity(tagDto);
        return tagDao.findByName(tag.getName()).isPresent();
    }

    @Override
    public void delete(Long id) {
        tagDao.delete(id);
    }

    @Override
    public TagDto findByName(String name) {
        Optional<Tag> tagOptional = tagDao.findByName(name);
        if (tagOptional.isEmpty()) {
            throw new CustomException(ErrorCode.TAG_NOT_FOUND, NAME, name);
        }
        return tagOptional.map(mapper::mapEntityToDto).get();
    }
}
