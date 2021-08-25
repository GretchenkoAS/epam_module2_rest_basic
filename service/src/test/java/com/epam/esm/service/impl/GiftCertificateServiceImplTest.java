package com.epam.esm.service.impl;


import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.QueryDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Query;
import com.epam.esm.entity.Tag;
import com.epam.esm.exeption.CustomException;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.QueryMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.GiftCertificateValidator;
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

public class GiftCertificateServiceImplTest {

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private TagService tagService;
    @Mock
    private GiftCertificateMapper mapper;
    @Mock
    private GiftCertificateValidator validator;
    @Mock
    private QueryMapper queryMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addGiftCertificatePositiveTest() {
        GiftCertificateDto expectedDto = new GiftCertificateDto();
        expectedDto.setName("GiftCertificate");
        GiftCertificate expected = new GiftCertificate();
        expected.setName("GiftCertificate");

        when(validator.isValidName(expectedDto.getName())).thenReturn(true);
        when(validator.isValidDescription(expectedDto.getDescription())).thenReturn(true);
        when(validator.isValidPrice(expectedDto.getPrice())).thenReturn(true);
        when(validator.isValidDuration(expectedDto.getDuration())).thenReturn(true);
        when(mapper.mapDtoToEntity(expectedDto)).thenReturn(expected);
        when(giftCertificateDao.findByName(expected.getName())).thenReturn(Optional.empty());
        when(giftCertificateDao.add(expected)).thenReturn(expected);

        when(mapper.mapEntityToDto(expected)).thenReturn(expectedDto);

        GiftCertificateDto actual = giftCertificateService.add(expectedDto);
        assertEquals(expectedDto, actual);
        verify(giftCertificateDao, times(1)).add(any(GiftCertificate.class));
    }

    @Test
    void addGiftCertificateNegativeTest() {
        GiftCertificateDto expectedDto = new GiftCertificateDto();
        expectedDto.setName("GiftCertificate");
        GiftCertificate expected = new GiftCertificate();
        expected.setName("GiftCertificate");

        when(validator.isValidName(expectedDto.getName())).thenReturn(false);
        assertThrows(CustomException.class,() -> {
            giftCertificateService.add(expectedDto);
        } );
        verify(giftCertificateDao,  never()).add(any(GiftCertificate.class));
    }

    @Test
    public void findPositiveTest() {
        Long id = 1L;
        GiftCertificateDto expectedDto = new GiftCertificateDto();
        expectedDto.setName("GiftCertificate");
        expectedDto.setId(id);
        GiftCertificate expected = new GiftCertificate();
        expected.setName("GiftCertificate");
        expected.setId(id);
        when(giftCertificateDao.findOne(id)).thenReturn(Optional.of(expected));
        when(mapper.mapEntityToDto(expected)).thenReturn(expectedDto);
        GiftCertificateDto actual = giftCertificateService.find(id);
        assertEquals(expectedDto, actual);
        verify(giftCertificateDao, times(1)).findOne(id);
    }

    @Test
    public void findNegativeTest() {
        Long id = 1L;
        when(giftCertificateDao.findOne(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> {
            giftCertificateService.find(id);
        });
        verify(giftCertificateDao, times(1)).findOne(id);
    }

    @Test
    public void findByNamePositiveTest() {
        String name = "GiftCertificate";
        GiftCertificateDto expectedDto = new GiftCertificateDto();
        expectedDto.setName(name);
        GiftCertificate expected = new GiftCertificate();
        expected.setName(name);
        when(giftCertificateDao.findByName(name)).thenReturn(Optional.of(expected));
        when(mapper.mapEntityToDto(expected)).thenReturn(expectedDto);
        GiftCertificateDto actual = giftCertificateService.findByName(name);
        assertEquals(expectedDto, actual);
        verify(giftCertificateDao, times(1)).findByName(name);
    }

    @Test
    public void findByNameNegativeTest() {
        String name = "GiftCertificate";
        when(giftCertificateDao.findByName(name)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> {
            giftCertificateService.findByName(name);
        });
        verify(giftCertificateDao, times(1)).findByName(name);
    }

    @Test
    void findAllTest() {
        GiftCertificate expected1 = new GiftCertificate();
        expected1.setName("Certificate1");
        expected1.setId(1L);
        GiftCertificate expected2 = new GiftCertificate();
        expected2.setName("Certificate2");
        expected2.setId(2L);
        List<GiftCertificate> listExpected = new ArrayList<>();
        listExpected.add(expected1);
        listExpected.add(expected2);
        GiftCertificateDto expectedDto1 = new GiftCertificateDto();
        expectedDto1.setName("Certificate1");
        expectedDto1.setId(1L);
        GiftCertificateDto expectedDto2 = new GiftCertificateDto();
        expectedDto2.setName("Certificate2");
        expectedDto2.setId(2L);
        List<GiftCertificateDto> listDtoExpected = new ArrayList<>();
        listDtoExpected.add(expectedDto1);
        listDtoExpected.add(expectedDto2);

        when(giftCertificateDao.findAll()).thenReturn(listExpected);
        when(giftCertificateDao.getTags(expected1.getId())).thenReturn(any(List.class));
        when(giftCertificateDao.getTags(expected2.getId())).thenReturn(any(List.class));
        when(mapper.mapListEntityToListDto(listExpected)).thenReturn(listDtoExpected);
        List<GiftCertificateDto> actual = giftCertificateService.findAll();

        assertEquals(listDtoExpected, actual);
        verify(giftCertificateDao, times(1)).findAll();
    }

    @Test
    public void findByQueryPositiveTest() {
        Query query = new Query();
        query.setContains("Query");
        QueryDto queryDto = new QueryDto();
        queryDto.setContains("Query");
        GiftCertificate expected1 = new GiftCertificate();
        expected1.setName("Certificate1");
        expected1.setId(1L);
        GiftCertificate expected2 = new GiftCertificate();
        expected2.setName("Certificate2");
        expected2.setId(2L);
        List<GiftCertificate> listExpected = new ArrayList<>();
        listExpected.add(expected1);
        listExpected.add(expected2);
        GiftCertificateDto expectedDto1 = new GiftCertificateDto();
        expectedDto1.setName("Certificate1");
        expectedDto1.setId(1L);
        GiftCertificateDto expectedDto2 = new GiftCertificateDto();
        expectedDto2.setName("Certificate2");
        expectedDto2.setId(2L);
        List<GiftCertificateDto> listDtoExpected = new ArrayList<>();
        listDtoExpected.add(expectedDto1);
        listDtoExpected.add(expectedDto2);

        when(queryMapper.mapDtoToEntity(queryDto)).thenReturn(query);
        when(giftCertificateDao.findByQuery(query)).thenReturn(listExpected);
        when(giftCertificateDao.getTags(expected1.getId())).thenReturn(any(List.class));
        when(giftCertificateDao.getTags(expected2.getId())).thenReturn(any(List.class));
        when(mapper.mapListEntityToListDto(listExpected)).thenReturn(listDtoExpected);
        List<GiftCertificateDto> actual = giftCertificateService.findByQuery(queryDto);

        assertEquals(listDtoExpected, actual);
        verify(giftCertificateDao, times(1)).findByQuery(query);
    }

//    @Test
//    void updatePositiveTest() {
//        Long id = 1L;
//        GiftCertificateDto expectedDto = new GiftCertificateDto();
//        expectedDto.setName("GiftCertificate");
//        GiftCertificate expected = new GiftCertificate();
//        expected.setName("GiftCertificate");
//
//        when(validator.isValidName(expectedDto.getName())).thenReturn(true);
//        when(validator.isValidDescription(expectedDto.getDescription())).thenReturn(true);
//        when(validator.isValidPrice(expectedDto.getPrice())).thenReturn(true);
//        when(validator.isValidDuration(expectedDto.getDuration())).thenReturn(true);
//        when(mapper.mapDtoToEntity(expectedDto)).thenReturn(expected);
//        when(giftCertificateDao.update(expected, id)).thenReturn(expected);
//        when(giftCertificateService.exist(expectedDto, id)).thenReturn(true);
//        when(mapper.mapEntityToDto(expected)).thenReturn(expectedDto);
//        GiftCertificateDto actual = giftCertificateService.update(expectedDto, id);
//        assertEquals(expectedDto, actual);
//        verify(giftCertificateDao, times(1)).update(any(GiftCertificate.class), any(Long.class));
//    }
//
//    @Test
//    void updateNegativeTest() {
//        GiftCertificateDto expectedDto = new GiftCertificateDto();
//        expectedDto.setName("GiftCertificate");
//        GiftCertificate expected = new GiftCertificate();
//        expected.setName("GiftCertificate");
//
//        when(validator.isValidName(expectedDto.getName())).thenReturn(false);
//        assertThrows(CustomException.class,() -> {
//            giftCertificateService.add(expectedDto);
//        } );
//        verify(giftCertificateDao,  never()).add(any(GiftCertificate.class));
//    }

}
