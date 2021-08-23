package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagService tagService;
    private final GiftCertificateMapper mapper;
    private final TagMapper tagMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService,
                                      GiftCertificateMapper mapper, TagMapper tagMapper) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
        this.mapper = mapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public void add(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = mapper.mapDtoToEntity(giftCertificateDto);
        if (giftCertificateDao.findByName(giftCertificate.getName()).isPresent()) {
            //fixme add error
        }
        giftCertificateDao.add(giftCertificate);
        addTags(giftCertificateDao.findByName(giftCertificate.getName()).get().getId(), giftCertificateDto.getTags());
    }

    @Override
    public GiftCertificateDto find(Long id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findOne(id);
        if (giftCertificateOptional.isEmpty()) {
            //fixme add error
        }
        GiftCertificate giftCertificate = giftCertificateOptional.get();
        List<Tag> tags = giftCertificateDao.getTags(id);
        giftCertificate.setTags(tags);
        return mapper.mapEntityToDto(giftCertificate);
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findAll();
        for (GiftCertificate giftCertificate : giftCertificates) {
            List<Tag> tags = giftCertificateDao.getTags(giftCertificate.getId());
            giftCertificate.setTags(tags);
        }
        return giftCertificates.stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto findByName(String name) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findByName(name);
        if (giftCertificateOptional.isEmpty()) {
            //fixme add throw
        }
        return giftCertificateOptional.map(mapper::mapEntityToDto).get();
    }

    @Override
    public void delete(Long id) {
        //fixme add throw
        giftCertificateDao.delete(id);
        giftCertificateDao.clearTags(id);
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto, Long id) {
        return null;
    }

    private void addTags(Long giftCertificateId, List<TagDto> tags) {
        if (tags != null && giftCertificateId != null) {
            List<TagDto> distinctTags = tags.stream().distinct().collect(Collectors.toList());
            for (TagDto tagDto : distinctTags) {
                if (!tagService.exist(tagDto)) {
                    tagService.add(tagDto);
                }
                giftCertificateDao.addTag(tagMapper.mapDtoToEntity(tagService.findByName(tagDto.getName())).getId(),
                        giftCertificateId);
            }
        }
    }
}