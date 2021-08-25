package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exeption.CustomException;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.validator.TagValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TagServiceImplTest {
    @InjectMocks
    private TagServiceImpl tagService;
    @Mock
    private TagDaoImpl tagDao;
    @Mock
    private TagMapper mapper;
    @Mock
    private TagValidator validator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findPositiveTest() {
        Long id = 1L;
        TagDto expectedDto = new TagDto();
        expectedDto.setName("Tag");
        expectedDto.setId(id);
        Tag expected = new Tag();
        expected.setName("Tag");
        expected.setId(id);
        when(tagDao.findOne(id)).thenReturn(Optional.of(expected));
        when(mapper.mapEntityToDto(expected)).thenReturn(expectedDto);
        TagDto actual = tagService.find(id);
        assertEquals(expectedDto, actual);
        verify(tagDao, times(1)).findOne(id);
    }

    @Test
    public void findNegativeTest() {
        Long id = 1L;
        when(tagDao.findOne(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> {
            tagService.find(id);
        });
        verify(tagDao, times(1)).findOne(id);
    }

    @Test
    void findAllPositiveTest() {
        Tag expected1 = new Tag();
        expected1.setName("Tag1");
        expected1.setId(1L);
        Tag expected2 = new Tag();
        expected2.setName("Tag2");
        expected2.setId(2L);
        List<Tag> listExpected = new ArrayList<>();
        listExpected.add(expected1);
        listExpected.add(expected2);
        TagDto expectedDto1 = new TagDto();
        expectedDto1.setName("Tag1");
        expectedDto1.setId(1L);
        TagDto expectedDto2 = new TagDto();
        expectedDto2.setName("Tag2");
        expectedDto2.setId(2L);
        List<TagDto> listDtoExpected = new ArrayList<>();
        listDtoExpected.add(expectedDto1);
        listDtoExpected.add(expectedDto2);

        when(tagDao.findAll()).thenReturn(listExpected);
        when(mapper.mapEntityToDto(expected1)).thenReturn(expectedDto1);
        when(mapper.mapEntityToDto(expected2)).thenReturn(expectedDto2);
        List<TagDto> actual = tagService.findAll();

        assertEquals(listDtoExpected, actual);
        verify(tagDao, times(1)).findAll();
    }

    @Test
    void addTagPositiveTest() {
        TagDto expectedDto = new TagDto();
        expectedDto.setName("Tag");
        Tag expected = new Tag();
        expected.setName("Tag");

        when(validator.isValidName(expectedDto.getName())).thenReturn(true);
        when(mapper.mapDtoToEntity(expectedDto)).thenReturn(expected);
        when(tagDao.findByName(expected.getName())).thenReturn(Optional.empty());
        when(tagDao.add(expected)).thenReturn(expected);
        when(mapper.mapEntityToDto(expected)).thenReturn(expectedDto);

        TagDto actual = tagService.add(expectedDto);

        assertEquals(expectedDto, actual);
        verify(tagDao, times(1)).add(any(Tag.class));
    }

    @Test
    void addTagNegativeTest() {
        TagDto expectedDto = new TagDto();
        expectedDto.setName("Tag");
        Tag expected = new Tag();
        expected.setName("Tag");

        when(validator.isValidName(expectedDto.getName())).thenReturn(false);
        assertThrows(CustomException.class,() -> {
            tagService.add(expectedDto);
        } );
        verify(tagDao,  never()).add(any(Tag.class));
    }

    @Test
    public void findByNamePositiveTest() {
        Long id = 1L;
        String name = "Tag";
        TagDto expectedDto = new TagDto();
        expectedDto.setName(name);
        expectedDto.setId(id);
        Tag expected = new Tag();
        expected.setName(name);
        expected.setId(id);
        when(tagDao.findByName(name)).thenReturn(Optional.of(expected));
        when(mapper.mapEntityToDto(expected)).thenReturn(expectedDto);
        TagDto actual = tagService.findByName(name);
        assertEquals(expectedDto, actual);
        verify(tagDao, times(1)).findByName(name);
    }

    @Test
    public void findByNameNegativeTest() {
        String name = "Tag";
        when(tagDao.findByName(name)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> {
            tagService.findByName(name);
        });
        verify(tagDao, times(1)).findByName(name);
    }
}
